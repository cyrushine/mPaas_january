package com.example.mpaasdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun Context.dp2px(dp: Int) =
    resources.displayMetrics.densityDpi * dp

fun ViewGroup.inflateInto(@LayoutRes layout: Int) {
    LayoutInflater.from(context).inflate(layout, this, true)
}

fun InputStream.closeQuietly() {
    try {
        close()
    } catch (ignored: Exception) {}
}

fun OutputStream.closeQuietly() {
    try {
        close()
    } catch (ignored: Exception) {}
}

/**
 * copy & close InputStream
 */
fun InputStream.copyTo(dest: File) {
    if (dest.exists()) dest.delete()
    dest.createNewFile()

    var o: OutputStream? = null
    try {
        o = dest.outputStream()
        this.copyTo(o)
        o.flush()
    } finally {
        this.closeQuietly()
        o?.closeQuietly()
    }
}