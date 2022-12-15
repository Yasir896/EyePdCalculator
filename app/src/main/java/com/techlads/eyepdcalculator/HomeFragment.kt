package com.techlads.eyepdcalculator

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentHomeBinding
import java.io.File
import java.lang.ref.WeakReference


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var latestTmpUri: Uri

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onPostInit() {
        binding.btRetake.setOnClickListener {
            if (askForPermissions(Manifest.permission.CAMERA)) {
                onCameraClick()
            }
        }

        binding.btOpenCamera.setOnClickListener {
            if (this::latestTmpUri.isInitialized) {
                latestTmpUri.let { uri ->
                val action = HomeFragmentDirections.actionHomeFragmentToCalculateFragment(uri)
                findNavController().navigate(action)
                }
            }
        }
    }


    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun askForPermissions(permission: String): Boolean {

        if (!isPermissionGranted(permission)) {
            if (shouldShowRequestPermissionRationale(permission)) {
                    Toast.makeText(requireContext(), "app_name_splash", Toast.LENGTH_SHORT).show()
            } else {
                cameraPermission.launch(permission)
            }
            return false
        }
        return true
    }

    private val cameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) onCameraClick()
    }


    private fun onCameraClick() {
        lifecycleScope.launchWhenStarted {
            getTmpFileUri(requireContext())?.let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)
            }
        }
    }

    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if(isSuccess) {
            latestTmpUri.let { uri ->
                binding.bgIv.setImageURI(uri)
            }
        }
    }

    fun getTmpFileUri(context: Context): Uri? {

        val contextRef = WeakReference(context)

        return contextRef.get()?.let {
            val tmpFile = File.createTempFile("tmp_image_file", ".png", context.cacheDir).apply {
                createNewFile()
                deleteOnExit()
            }

            FileProvider.getUriForFile(
                context,
                "${BuildConfig.APPLICATION_ID}.provider",
                tmpFile
            )
        }
    }
}