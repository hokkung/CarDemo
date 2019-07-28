package com.example.hokkung.cardemo.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.activity.ShopDetailActivity
import com.example.hokkung.cardemo.adapter.ShopCarAdapter
import com.example.hokkung.cardemo.extension.launchActivity
import com.example.hokkung.cardemo.model.Shop
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import kotlinx.android.synthetic.main.fragment_car.view.*


class CarFragment : Fragment(), ShopCarAdapter.onCardClickListener {


    private val TAG by lazy { "CarFragment" }
    private var vm: MainMenuViewModel? = null
    private var shopCarAdapter: ShopCarAdapter = ShopCarAdapter(this)


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
        view.recyclerView.adapter = shopCarAdapter
    }

    private fun initRefreshView(view: View) {
        view.swipeRefreshView.setOnRefreshListener {
            fetchAllShopFromServer()
        }
    }

    private fun getAllShop() {
        vm?.getAllShop()?.observe(this, Observer {
            Log.v(TAG, it.toString())
            shopCarAdapter.submitList(it)
            shopCarAdapter.notifyDataSetChanged()
            disabledRefreshView()
        })
    }

    private fun fetchAllShopFromServer() {
        vm?.fetchAllShopFromServer()
    }

    private fun disabledRefreshView() {
        activity?.runOnUiThread {
            view?.swipeRefreshView?.let {
                if (it.isRefreshing) it.isRefreshing = false
            }
        }
    }

    override fun onCardItemClick(item: Shop) {
        activity?.launchActivity(ShopDetailActivity::class.java)
    }
}
