package com.example.mpaasdemo

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

val mainHandler by lazy {
    Handler(Looper.getMainLooper())
}

val workerHandler by lazy {
    val thread = HandlerThread("worker-main")
    thread.start()
    Handler(thread.looper)
}