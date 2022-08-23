package com.techlads.eyepdcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentCalculatePdBinding
import kotlin.math.abs
import kotlin.math.sqrt


class CalculatePdFragment : BaseFragment<FragmentCalculatePdBinding>() {

    val  navArg: CalculatePdFragmentArgs by navArgs()

    var markPupil = true;

    val creditCardWidthMm : Float = 85.60f
    var pupilsDistance = 0f
    var cardDistance = 0f


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalculatePdBinding {
        return FragmentCalculatePdBinding.inflate(inflater, container, false)
    }

    override fun onPostInit() {
        navArg.imageUri.let { it
            binding.bgIv.setImageURI(it)
        }
        setListeners()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        binding.markerPupilOne.setTouchListener()
        binding.markerPupilTwo.setTouchListener()
        binding.markerCardOne.setTouchListener()
        binding.markerCardTwo.setTouchListener()

        binding.btGotoNext.setOnClickListener {

            if (markPupil) {
                val marker1 = binding.markerPupilOne
                val marker2 = binding.markerPupilTwo

                pupilsDistance = sqrt(abs(
                    (marker2.x - marker1.x) * (marker2.x - marker1.x) +
                            (marker2.y - marker1.y) * (marker2.y - marker1.y)
                ))

                changeMarkers()
                markPupil = false
                return@setOnClickListener

            } else if (!markPupil ) {
                val marker1 = binding.markerCardOne
                val marker2 = binding.markerCardTwo

                cardDistance = sqrt(abs(
                    (marker2.x - marker1.x) * (marker2.x - marker1.x) +
                            (marker2.y - marker1.y) * (marker2.y - marker1.y)
                ))

                val pupillaryDistance = ((creditCardWidthMm / cardDistance) * pupilsDistance)

                Toast.makeText(requireContext(),
                    "distance: $pupillaryDistance",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),
                    "Something wrong! please make sure you followed all steps correctly.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeMarkers() {
        binding.markerPupilOne.hide()
        binding.markerPupilTwo.hide()

        binding.markerCardOne.show()
        binding.markerCardTwo.show()
    }


    fun View.hide() {
        this.visibility = View.INVISIBLE
    }
    fun View.show() {
        this.visibility = View.VISIBLE
    }


    @SuppressLint("ClickableViewAccessibility")
    fun ImageView.setTouchListener() {
        this.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.y = motionEvent.rawY - view.height / 2
                view.x = motionEvent.rawX - view.width / 2
            }
            true
        }
    }
}