package com.example.hokkung.cardemo.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.extension.launchActivity
import com.example.hokkung.cardemo.extension.launchFragment
import com.example.hokkung.cardemo.extension.log
import com.example.hokkung.cardemo.fragment.CarFragment
import com.example.hokkung.cardemo.fragment.SettingFragment
import com.example.hokkung.cardemo.pref.SharedPrefManager
import com.example.hokkung.cardemo.utils.BaseActivity
import com.example.hokkung.cardemo.utils.LocaleManager
import com.example.hokkung.cardemo.viewmodel.MainMenuViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_menu.*
import java.util.*

class MainMenuActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val tag by lazy { "MainMenuActivity" }
    private val carFragment by lazy { CarFragment() }
    private val favouriteFragment by lazy { SettingFragment() }
    private var vm: MainMenuViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initView()
        initBottomNavigate()
        initViewModel()
        log(tag, SharedPrefManager.getLanguagePref(applicationContext))
        log(tag, Locale.getDefault().language)

    }

    private fun initView() {
        title = ""
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.add_menu -> {
                launchActivity(AddShopActivity::class.java)
                true
            }
            R.id.search_menu -> {
                launchActivity(SearchActivity::class.java)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
