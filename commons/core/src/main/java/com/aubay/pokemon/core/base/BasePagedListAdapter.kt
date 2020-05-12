package com.aubay.pokemon.core.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter<T>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean = { old, new -> old == new }
) : PagedListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = itemsSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = contentsSame(oldItem, newItem)
}) {
    abstract val itemLayout: Int

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                bindItemViewHolder(holder, position)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> {
                createItemViewHolder(parent)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }

    override fun getItemViewType(position: Int) = when {
        isPositionItem(position) -> TYPE_ITEM
        else -> throw IllegalArgumentException("Unsupported View Type")
    }

    open fun createItemViewHolder(parent: ViewGroup): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)

        return object : RecyclerView.ViewHolder(view) {}
    }

    abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    open fun isPositionItem(position: Int): Boolean = true


    companion object {
        const val TYPE_ITEM = 0
    }
}