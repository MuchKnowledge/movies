package com.example.app.utils.extension

import android.content.Context
import androidx.core.content.ContextCompat

infix fun Context.drawable(id: Int) = ContextCompat.getDrawable(this, id)
