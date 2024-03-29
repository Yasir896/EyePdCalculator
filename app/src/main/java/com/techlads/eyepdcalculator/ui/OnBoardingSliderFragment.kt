package com.techlads.eyepdcalculator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.techlads.eyepdcalculator.R
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
                    binding.btnNext.visibility = View.INVISIBLE
                }
            }
        }
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallBack)
    }
}