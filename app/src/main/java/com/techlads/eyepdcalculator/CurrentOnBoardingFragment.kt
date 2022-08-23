package com.techlads.eyepdcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentCurrentOnBoardingBinding
import com.techlads.eyepdcalculator.databinding.FragmentOnBoardingSliderBinding


const val CURRENT_SLIDE_TEXT = "current_slide_text"
class CurrentOnBoardingFragment : BaseFragment<FragmentCurrentOnBoardingBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCurrentOnBoardingBinding {
        return FragmentCurrentOnBoardingBinding.inflate(inflater, container, false)
    }

    override fun onPostInit() {
        val text = arguments?.getString(CURRENT_SLIDE_TEXT)

        text.let {
            binding.guideLineText.text = it
        }
    }
}