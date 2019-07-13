package com.example.hokkung.cardemo.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.adapter.ShopCarAdapter
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import kotlinx.android.synthetic.main.fragment_car.view.*


class CarFragment : Fragment() {

    private var vm: MainMenuViewModel? = null
    private var shopCarAdapter: ShopCarAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_car, container, false)
        initRecyclerView(view)
        initRefreshView(view)
        initViewModel()
        return view
    }

    private fun initViewModel() {
        vm = activity?.run {
            ViewModelProviders.of(this).get(MainMenuViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        getAllShop()
    }

    private fun initRecyclerView(view: View) {
        view.recyclerView.layoutManager = LinearLayoutManager(view.context)
        shopCarAdapter = ShopCarAdapter()
        view.recyclerView.adapter = shopCarAdapter
    }

    private fun initRefreshView(view: View) {
        view.swipeRefreshView.setOnRefreshListener {
            getAllShop()
        }
    }

    private fun getAllShop() {
        vm?.getAllShop()?.  observe(this, Observer {
            shopCarAdapter?.submitList(it)
            disabledRefreshView()
        })
    }

    private fun disabledRefreshView() {
        activity?.runOnUiThread {
            view?.swipeRefreshView?.let {
                if (it.isRefreshing) it.isRefreshing = false
            }
        }
    }

}
