package com.example.app.utils

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import com.example.app.utils.extension.dpToPx

class CornersOutlineProvider(
    private val roundCorners: RoundCorners = RoundCorners.ALL,
    private val radius: Float = 4F,
) : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {

        val left = 0
        val top = 0
        val right = view.width
        val bottom = view.height
        val cornerRadius = radius.dpToPx

        when (roundCorners) {
            RoundCorners.ALL -> outline.setRoundRect(left, top, right, bottom, cornerRadius.toFloat())
            RoundCorners.LEFT_SIDE -> outline.setRoundRect(left, top, right + cornerRadius, bottom, cornerRadius.toFloat())
            RoundCorners.RIGHT_SIDE -> outline.setRoundRect(left - cornerRadius, top, right, bottom, cornerRadius.toFloat())
            RoundCorners.TOP -> outline.setRoundRect(left, top, right, bottom + cornerRadius, cornerRadius.toFloat())
            RoundCorners.BOTTOM -> outline.setRoundRect(left, top - cornerRadius, right, bottom, cornerRadius.toFloat())
        }
    }
}

enum class RoundCorners { ALL, LEFT_SIDE, RIGHT_SIDE, TOP, BOTTOM }