package com.example.hokkung.cardemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.utils.RoundedBottomSheetDialogFragment

class FilterFragment : RoundedBottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_filter, container, false)
        return view
    }
}