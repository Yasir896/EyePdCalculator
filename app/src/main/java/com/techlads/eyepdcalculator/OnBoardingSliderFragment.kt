package com.techlads.eyepdcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.slider.Slider
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentOnBoardingSliderBinding



class OnBoardingSliderFragment : BaseFragment<FragmentOnBoardingSliderBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnBoardingSliderBinding {
        return FragmentOnBoardingSliderBinding.inflate(inflater, container, false)
    }

    override fun onPostInit() {
        val pagerAdapter = SliderAdapter(requireActivity())
        binding.viewPager.adapter = pagerAdapter

        binding.indicator.setViewPager(binding.viewPager)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
        }

        val pageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                if (position == 3) {
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btnNext.visibility = View.GONE
                }
            }
        }
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallBack)
    }
}