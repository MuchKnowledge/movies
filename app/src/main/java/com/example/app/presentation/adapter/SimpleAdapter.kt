package com.example.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter<Item>(
    private var items: List<Item>,
    @LayoutRes private val layout: Int,
    private val bindItem: View.(item: Item, position: Int) -> Unit,
) : RecyclerView.Adapter<SimpleAdapter.BaseViewHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) = super.setHasStableIds(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return BaseViewHolder(item)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        bindItem(holder.itemView, item, position)
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}