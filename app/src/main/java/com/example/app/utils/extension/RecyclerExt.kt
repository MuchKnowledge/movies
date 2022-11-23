package com.example.app.utils

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.app.presentation.adapter.SimpleAdapter

fun <Item> RecyclerView.quickInit(
    items: List<Item>,
    @LayoutRes itemLayout: Int,
    bind: View.(Item, Int) -> Unit
) {
    adapter = SimpleAdapter(items, itemLayout) { item: Item, position: Int -> bind.invoke(this, item, position) }
}
