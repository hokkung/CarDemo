package com.example.hokkung.cardemo.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.activity.AddShopActivity
import com.example.hokkung.cardemo.adapter.FavouriteAdapter
import com.example.hokkung.cardemo.extension.launchActivity
import com.example.hokkung.cardemo.pref.SharedPrefManager
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*


class SettingFragment : Fragment() {
    private lateinit var favouriteAdapter: FavouriteAdapter
    private var vm: MainMenuViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        initViewModel()
        initRecyclerView(view)
        initLanguageSetting(view)
        return view
    }

    private fun initViewModel() {
        vm = activity?.run {
            ViewModelProviders.of(this).get(MainMenuViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        vm?.getAllFavouriteShop()?.observe(this, Observer {
            favouriteAdapter.submitList(it)
        })
    }

    private fun initRecyclerView(view: View) {
        view.favouriteRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        favouriteAdapter = FavouriteAdapter()
        view.favouriteRecyclerView.adapter = favouriteAdapter
    }

    private fun initLanguageSetting(view: View) {
        view.language.text = SharedPrefManager.getLanguagePref(view.context)

        view.settingAddShop.setOnClickListener {
            activity?.launchActivity(AddShopActivity::class.java)
        }

        view.settingLanguage.setOnClickListener {
            launchBottomDialog()
        }
    }

    private fun launchBottomDialog() {
        val languageFragment = LanguageFragment()
        languageFragment.show(fragmentManager!!, languageFragment.tag)

    }


}
