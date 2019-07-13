package com.example.hokkung.cardemo.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.pref.SharedPrefManager
import java.util.*


object LocaleManager {
    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_THAI = "th"
    const val LANGUAGE_KEY = "language_key"

    fun setLocale(context: Context): Context {
        return currentResource(context, SharedPrefManager.getLanguagePref(context))
    }

    fun setNewLocale(context: Context, key: String): Context {
        SharedPrefManager.setLanguagePref(context, key)
        return currentResource(context, key)
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
    }

    fun mappingLanguage(context: Context, language: String): String {
        val languages = context.resources.getStringArray(R.array.languages)
        return when (language) {
            languages[0] -> LANGUAGE_THAI
            languages[1] -> LANGUAGE_ENGLISH
            else -> LANGUAGE_THAI
        }
    }

    fun currentResource(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)

        if (Build.VERSION.SDK_INT >= 19) {
            config.setLocale(locale)
            val contextNew = context.createConfigurationContext(config)
            return contextNew
        }
        config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)

        return context

    }
}