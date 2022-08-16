package com.techlads.eyepdcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techlads.eyepdcalculator.databinding.MyDialogBinding


/**
 * Created by Yasir on 7/21/2022.
 */
class MyDialog: BottomSheetDialogFragment() {

    val args: MyDialogArgs by navArgs()
    lateinit var binding: MyDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = args.itemId
        binding.messageTv.text = text
    }

}