package com.example.app.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithGlide(uri: String) {
    Glide.with(this)
        .load(uri)
        .into(this)
}