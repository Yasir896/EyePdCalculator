package com.techlads.eyepdcalculator

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techlads.eyepdcalculator.base.BaseFragment
import com.techlads.eyepdcalculator.databinding.FragmentSplashBinding


class SplashFragment: BaseFragment<FragmentSplashBinding>()  {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onPostInit() {
        goToNextScreen()
    }

    private fun goToNextScreen() {
        object : CountDownTimer(1000, 100) {
            override fun onTick(p0: Long) { }
            override fun onFinish() {
                navigateTo(R.id.action_splashFragment_to_onboardingFragment)
            }

        }.start()
    }

    private fun navigateTo(destinationId: Int) {
        findNavController().navigate(destinationId)
    }

}