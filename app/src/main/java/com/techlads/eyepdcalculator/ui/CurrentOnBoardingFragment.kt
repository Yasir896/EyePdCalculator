package com.techlads.eyepdcalculator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techlads.eyepdcalculator.utils.Constants.CURRENT_SLIDE_TEXT
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentCurrentOnBoardingBinding

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