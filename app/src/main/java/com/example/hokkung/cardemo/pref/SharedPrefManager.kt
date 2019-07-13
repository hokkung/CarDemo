package com.example.hokkung.cardemo.pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.hokkung.cardemo.utils.LocaleManager

object SharedPrefManager {
    const val MY_PREFERENCES = "my_pref"
    private var pref: SharedPreferences? = null

    fun initSharedPrefManager(context: Context) {
        pref = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)
    }

    @SuppressLint("CommitPrefEdits")
    fun setLanguagePref(context: Context, localeKey: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        with (pref.edit()) {
            putString(LocaleManager.LANGUAGE_KEY, localeKey)
            apply()
        }
    }

    fun getLanguagePref(context: Context): String {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getString(LocaleManager.LANGUAGE_KEY, LocaleManager.LANGUAGE_THAI)
    }

}