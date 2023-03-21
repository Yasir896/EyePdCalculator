package com.techlads.eyepdcalculator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.techlads.eyepdcalculator.R

class SliderAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = CurrentOnBoardingFragment()
        when(position) {
            0 -> {
                fragment.arguments = Bundle().apply {
                    putString(CURRENT_SLIDE_TEXT, guideLines[0])
                }
            }
            1 -> {
                fragment.arguments = Bundle().apply {
                    putString(CURRENT_SLIDE_TEXT, guideLines[1])
                }
            }
            2 -> {
                fragment.arguments = Bundle().apply {
                    putString(CURRENT_SLIDE_TEXT, guideLines[2])
                }
            }
            3 -> {
                fragment.arguments = Bundle().apply {
                    putString(CURRENT_SLIDE_TEXT, guideLines[3])
                }
            }
        }
        return fragment
    }

    private val guideLines: Array<String> = arrayOf(
        fa.getString(R.string.slide_one_info),
        fa.getString(R.string.slide_two_info),
        fa.getString(R.string.slide_three_info),
        fa.getString(R.string.slide_four_info),
    )
}