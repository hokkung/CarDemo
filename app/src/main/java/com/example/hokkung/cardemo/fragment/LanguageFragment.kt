package com.example.hokkung.cardemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat.recreate
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.extension.toast
import com.example.hokkung.cardemo.utils.LocaleManager
import com.example.hokkung.cardemo.utils.RoundedBottomSheetDialogFragment
import kotlinx.android.synthetic.main.language_fragment.view.*


class LanguageFragment : RoundedBottomSheetDialogFragment() {
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.language_fragment, container, false)
        initListView(view)
        return view
    }

    private fun initListView(view: View) {
        val languages = resources.getStringArray(R.array.languages)
        adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, languages)
        view.languageListView.adapter = adapter

        view.languageListView.setOnItemClickListener { _, _, i, _ ->
            val item = adapter.getItem(i)
            val language = LocaleManager.mappingLanguage(view.context ,item)
            when (language) {
                LocaleManager.LANGUAGE_ENGLISH -> {
                    LocaleManager.setNewLocale(activity?.baseContext!!, LocaleManager.LANGUAGE_ENGLISH)
                    activity?.recreate()
                    dismiss()
                }
                LocaleManager.LANGUAGE_THAI -> {
                    LocaleManager.setNewLocale(activity?.baseContext!!, LocaleManager.LANGUAGE_THAI)
                    activity?.recreate()
                    dismiss()
                }
                else -> {
                    LocaleManager.setNewLocale(activity?.baseContext!!, LocaleManager.LANGUAGE_THAI)
                    activity?.recreate()
                    dismiss()
                }
            }
        }
    }
}