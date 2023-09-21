package com.example.environmentnews.utilits

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.environmentnews.R

fun replaceFragmentMain(fagmnt: Fragment, aStack: Boolean = true) {
    if (aStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.main_layout,
                fagmnt
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_layout,
                fagmnt
            ).commit()
    }
}

@SuppressLint("ObsoleteSdkInt")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun setStatusBarGradiantMain(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        val background = ContextCompat.getDrawable(activity, R.color.background)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }
}