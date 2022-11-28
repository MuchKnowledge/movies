package com.example.app.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.R

fun ImageView.loadWithGlide(uri: String) {
    Glide.with(this)
        .load(uri)
        .placeholder(R.drawable.movie_placeholder_logo)
        .into(this)
}