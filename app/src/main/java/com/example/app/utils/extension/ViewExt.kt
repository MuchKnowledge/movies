package com.example.app.utils.extension

import android.view.View
import com.example.app.utils.CornersOutlineProvider
import com.example.app.utils.RoundCorners

fun View.roundCorners(roundCorners: RoundCorners = RoundCorners.ALL, radius: Float = 4f) {
    outlineProvider = CornersOutlineProvider(roundCorners, radius)
    clipToOutline = true
}
