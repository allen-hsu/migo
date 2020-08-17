package com.allen.migo.framework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(@LayoutRes layoutResId: Int, parent: ViewGroup) :
            this(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false))

    abstract fun bind(data: T)
    open fun rebind(data: T) {}

    abstract fun initObserve()

    abstract fun releaseObserve()
}