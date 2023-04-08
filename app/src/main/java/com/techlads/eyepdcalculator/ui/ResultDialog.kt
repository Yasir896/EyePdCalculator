package com.techlads.eyepdcalculator.ui


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.techlads.eyepdcalculator.Constatns
import com.techlads.eyepdcalculator.databinding.DialogPdResultLayoutBinding


/**
 * Created by Yasir on 7/21/2022.
 */
class ResultDialog: DialogFragment() {

    companion object {
        const val TAG = "ResultDialog"
    }

    var onCloseClickListener: OnCloseClickListener? = null

    private lateinit var binding: DialogPdResultLayoutBinding

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogPdResultLayoutBinding.inflate(LayoutInflater.from(context))
        getDataFromArguments()
        setListener()
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create().also {
                val window =it.window
                window?.attributes?.gravity = Gravity.CENTER
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    @SuppressLint("SetTextI18n")
    fun getDataFromArguments() {
        val result: String? = arguments?.getString(Constatns.PD_RESULT)
        binding.resultTv.text = "Your Pupillary distance is: $result"
    }

    fun setListener() {
        binding.btnClose.setOnClickListener {
            onCloseClickListener?.onClose(dialog)
        }
    }

    interface OnCloseClickListener {
        fun onClose(dialogFragment: Dialog?)
    }
}