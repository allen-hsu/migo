package com.allen.migo.framework

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>() {

    private val itemsLock = Any()
    private val items = ArrayList<T>()
    private var lastPosition: Int = -1

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {
        onBind(holder, position)
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            onReBind(holder, position)
        }
    }

    override fun getItemCount(): Int {
        synchronized(itemsLock) {
            return items.size
        }
    }

    override fun onViewDetachedFromWindow(viewHolder: BaseRecyclerViewHolder<T>) {
        super.onViewDetachedFromWindow(viewHolder)
        cancelAnimationsRecursive(viewHolder.itemView)
    }

    open fun onBind(viewHolder: BaseRecyclerViewHolder<T>, position: Int) {
        getItem(position)?.apply {
            viewHolder.bind(this)
        }
    }

    open fun onReBind(viewHolder: BaseRecyclerViewHolder<T>, position: Int) {
        onBind(viewHolder, position)
    }

    fun getItems(): List<T> = items

    fun setItems(items: List<T>) {
        synchronized(itemsLock) {
            this.items.apply {
                clear()
                addAll(items)
            }
        }
    }

    fun setItem(position: Int, item: T) {
        synchronized(itemsLock) {
            this.items.set(position, item)
        }
    }

    fun getItem(position: Int): T? {
        synchronized(itemsLock) {
            return items.getOrNull(position)
        }
    }

    fun addItem(item: T) {
        synchronized(itemsLock) {
            this.items.add(item)
        }
    }

    fun addItems(items: List<T>) {
        synchronized(itemsLock) {
            this.items.addAll(items)
        }
    }

    fun addItems(position: Int, items: List<T>) {
        synchronized(itemsLock) {
            this.items.addAll(position, items)
        }
    }

    fun removeItem(position: Int) {
        synchronized(itemsLock) {
            items.removeAt(position)
        }
    }

    fun removeItem(item: T) {
        synchronized(itemsLock) {
            items.remove(item)
        }
    }

    fun clearItems() {
        synchronized(itemsLock) {
            items.clear()
        }
    }

    private fun cancelAnimationsRecursive(view: View?) {
        view?.let {
            if (it.hasTransientState()) {
                it.animate().cancel()
                if (it is ViewGroup) {
                    val count = it.childCount
                    var i = 0
                    while (it.hasTransientState() && i < count) {
                        cancelAnimationsRecursive(it.getChildAt(i))
                        i += 1
                    }
                }
            }
        }
    }

    protected fun getItemPosition(viewHolder: RecyclerView.ViewHolder): Int =
        viewHolder.layoutPosition

}