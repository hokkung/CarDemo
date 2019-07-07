package com.example.hokkung.cardemo.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.extension.launchActivity
import com.example.hokkung.cardemo.extension.launchFragment
import com.example.hokkung.cardemo.fragment.CarFragment
import com.example.hokkung.cardemo.fragment.MyFavouriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val carFragment by lazy { CarFragment() }
    private val favouriteFragment by lazy { MyFavouriteFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initView()
        initBottomNavigate()
    }

    private fun initView() {
        title = ""
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
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
            R.id.favourite_menu -> {
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
