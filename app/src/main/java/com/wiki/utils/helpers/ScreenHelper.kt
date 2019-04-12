package com.wiki.utils.helpers

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.widget.RelativeLayout

class ScreenHelper {

    companion object {

        fun setViewLayoutParams(activity: Activity, view: View, widthPercent: Double, heightPercent: Double) {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            view.layoutParams = RelativeLayout.LayoutParams(
                (widthPercent * displayMetrics.widthPixels).toInt(),
                (heightPercent * displayMetrics.heightPixels).toInt())
        }
    }
}