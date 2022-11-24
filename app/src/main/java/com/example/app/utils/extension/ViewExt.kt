package com.example.app.utils.extension

import android.os.SystemClock
import android.view.View
import com.example.app.utils.CornersOutlineProvider
import com.example.app.utils.RoundCorners

fun View.roundCorners(roundCorners: RoundCorners = RoundCorners.ALL, radius: Float = 4f) {
    outlineProvider = CornersOutlineProvider(roundCorners, radius)
    clipToOutline = true
}

inline fun View.safeClick(crossinline listener: () -> Unit) {
    var clickTime = 0L
    val throttleTime = 1000

    setOnClickListener {
        if (SystemClock.uptimeMillis() <= (clickTime + throttleTime)) return@setOnClickListener
        clickTime = SystemClock.uptimeMillis()
        listener.invoke()
    }
}