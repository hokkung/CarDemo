package com.example.hokkung.cardemo.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.maps.SupportMapFragment


class TouchMapFragment : SupportMapFragment() {
    private var mListener: OnTouchListener? = null

    override fun onCreateView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, savedInstance: Bundle?): View? {
        val layout = super.onCreateView(layoutInflater, viewGroup, savedInstance)
        val frameLayout = TouchableWrapper(activity!!)
        val color = ResourcesCompat.getColor(resources, android.R.color.transparent, null)
        frameLayout.setBackgroundColor(color)

        (layout as ViewGroup).addView(
            frameLayout,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )

        return layout
    }

    fun setListener(listener: OnTouchListener) {
        mListener = listener
    }

    interface OnTouchListener {
        fun onTouch()
    }

    inner class TouchableWrapper(context: Context) : FrameLayout(context) {

        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> mListener?.onTouch()
                MotionEvent.ACTION_UP -> mListener?.onTouch()
            }
            return super.dispatchTouchEvent(event)
        }
    }
}