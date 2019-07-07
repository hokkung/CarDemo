package com.example.hokkung.cardemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hokkung.cardemo.activity.MainMenuActivity
import com.example.hokkung.cardemo.extension.launchActivity

class MainActivity : AppCompatActivity() {


    companion object {

        private val REQUEST_PERMISSON by lazy { 0 }
        private val tag by lazy { "MainActivity" }
    }

    private val permissions by lazy {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            checkPermissions()
        }, 500)

    }

    private fun checkPermissions() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSON)
        } else {
            launchActivity(MainMenuActivity::class.java)
        }
    }

    private fun hasPermission(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSON -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(tag, grantResults.toString())
                    launchActivity(MainMenuActivity::class.java)
                    return
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
