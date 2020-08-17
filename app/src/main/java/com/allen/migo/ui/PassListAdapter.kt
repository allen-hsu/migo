package com.allen.migo.ui

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allen.migo.framework.BaseRecyclerViewAdapter
import com.allen.migo.framework.BaseRecyclerViewHolder
import com.allen.migo.logic.Pass

class PassListAdapter(
    private val onItemClicked: ((data: Pass) -> Unit)? = null,
    private val onPassBtnClicked: ((data: Pass) -> Unit)? = null,
    private val latestExpiredTime: LiveData<Long>? = null
) : BaseRecyclerViewAdapter<Pass>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Pass> {
        return PassViewHolder(
            parent,
            onItemClicked,
            onPassBtnClicked,
            latestExpiredTime
        )
    }

    override fun onViewAttachedToWindow(viewHolder: BaseRecyclerViewHolder<Pass>) {
        super.onViewAttachedToWindow(viewHolder)
        viewHolder.initObserve()
    }

    override fun onViewDetachedFromWindow(viewHolder: BaseRecyclerViewHolder<Pass>) {
        super.onViewDetachedFromWindow(viewHolder)
        viewHolder.releaseObserve()
    }
}