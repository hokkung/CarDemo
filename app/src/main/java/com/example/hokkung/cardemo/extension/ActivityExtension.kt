package com.example.hokkung.cardemo.extension

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hokkung.cardemo.R
import kotlinx.android.synthetic.main.activity_main_menu.view.*


fun AppCompatActivity.launchFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}

fun <T> Context.launchActivity(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}