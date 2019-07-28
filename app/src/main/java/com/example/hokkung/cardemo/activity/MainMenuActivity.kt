package com.example.hokkung.cardemo.activity

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.extension.delay
import com.example.hokkung.cardemo.extension.launchFragment
import com.example.hokkung.cardemo.extension.log
import com.example.hokkung.cardemo.extension.toast
import com.example.hokkung.cardemo.fragment.CarFragment
import com.example.hokkung.cardemo.fragment.SettingFragment
import com.example.hokkung.cardemo.pref.SharedPrefManager
import com.example.hokkung.cardemo.utils.BaseActivity
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_menu.*
import java.util.*


class MainMenuActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val tag by lazy { "MainMenuActivity" }
    private val carFragment = CarFragment()
    private val favouriteFragment = SettingFragment()
    private var vm: MainMenuViewModel? = null
    private var doubleBackPressed  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initView()
        initBottomNavigate()
        initViewModel()
        log(tag, Locale.getDefault().toString())
        log(tag, SharedPrefManager.getLanguagePref(applicationContext))
    }

    private fun initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun initViewModel() {
        vm = ViewModelProviders.of(this).get(MainMenuViewModel::class.java)
    }

    private fun initBottomNavigate() {
        launchFragment(carFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.car_menu -> {
                launchFragment(carFragment)
               true
            }
            R.id.setting_menu -> {
                launchFragment(favouriteFragment)
                true
            }
            else -> false
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }

        doubleBackPressed = true
        toast(getString(R.string.exit_app))
        delay(resetBackPressed, 2000)
    }

    private val resetBackPressed:() -> Unit = {
        doubleBackPressed = false
    }

}
