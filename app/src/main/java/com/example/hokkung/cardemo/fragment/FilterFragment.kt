package com.example.hokkung.cardemo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.adapter.CategoryAdapter
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.utils.RoundedBottomSheetDialogFragment
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import kotlinx.android.synthetic.main.fragment_filter.view.*

class FilterFragment(val vm: MainMenuViewModel?) : RoundedBottomSheetDialogFragment(), CategoryAdapter.OnCategoryCheckListener {


    private val defaultRange by lazy { 1 }
    private var categoryAdapter = CategoryAdapter(this)

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var seekBarRange: SeekBar
    private lateinit var range: TextView

    init {
        vm?.fetchAllCategoryFromServer()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_filter, container, false)
        binding(view)
        initRange()
        initRecyclerView()
        setCategories()
        return view
    }

    private fun initRecyclerView() {
        categoryRecyclerView.layoutManager = GridLayoutManager(view?.context, 2)
        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun setCategories() {
        vm?.getAllCategories()?.observe(this, Observer {
            categoryAdapter.submitList(it)

        })
    }

    private fun binding(view: View) {
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)
        seekBarRange = view.findViewById(R.id.seekBarRange)
        range = view.findViewById(R.id.range)
    }

    private fun initRange() {
        seekBarRange.progress = defaultRange
        setRangeText(defaultRange)
        seekBarRange.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                setRangeText(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }



    override fun onClickCheckbox(item: Category) {

    }

    @SuppressLint("SetTextI18n")
    private fun setRangeText(value: Int) {
        range.text = "${getString(R.string.range)} ($value) ${getString(R.string.km)}"
    }
}