package com.example.hokkung.cardemo

import com.example.hokkung.cardemo.pref.SharedPrefManager
import com.example.hokkung.cardemo.utils.MultiLanguageApp

class Application : MultiLanguageApp() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.initSharedPrefManager(applicationContext)
    }
}