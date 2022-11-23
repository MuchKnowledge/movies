package com.example.app.utils.extension

import android.content.res.Resources

val Float.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

