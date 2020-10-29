package com.example.mpaasdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Context.dp2px(dp: Int) =
    resources.displayMetrics.densityDpi * dp

fun ViewGroup.inflateInto(@LayoutRes layout: Int) {
    LayoutInflater.from(context).inflate(layout, this, true)
}