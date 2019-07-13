package com.example.hokkung.cardemo.extension

import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment

fun AppCompatDialogFragment.toast(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
}