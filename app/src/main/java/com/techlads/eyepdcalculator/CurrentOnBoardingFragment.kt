package com.techlads.eyepdcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


const val CURRENT_SLIDE_TEXT = "current_slide_text"
class CurrentOnBoardingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text = arguments?.getString(CURRENT_SLIDE_TEXT)

        text.let {
            view.findViewById<TextView>(R.id.guideLineText).text = it
        }

    }
}