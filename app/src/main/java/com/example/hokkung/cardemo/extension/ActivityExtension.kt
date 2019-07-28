package com.example.hokkung.cardemo.extension

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hokkung.cardemo.R
import kotlinx.android.synthetic.main.activity_main_menu.view.*
import java.util.function.Function
import java.util.logging.Handler


fun AppCompatActivity.launchFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}

fun <T> Context.launchActivity(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun AppCompatActivity.log(tag: String, message: String) {
    Log.v(tag, message)
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.delay(func: () -> Unit, time: Long) {
    android.os.Handler().postDelayed( { func() }, time)
}