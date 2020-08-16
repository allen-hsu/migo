package com.allen.migo.ui

import android.view.ViewGroup
import com.allen.migo.framework.BaseRecyclerViewAdapter
import com.allen.migo.framework.BaseRecyclerViewHolder
import com.allen.migo.logic.Pass

class PassListAdapter(
    private val onItemClicked: ((data: Pass) -> Unit)? = null,
    private val onPassBtnClicked: ((data: Pass) -> Unit)? = null
) :
    BaseRecyclerViewAdapter<Pass>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Pass> {
        return PassViewHolder(
            parent,
            onItemClicked,
            onPassBtnClicked
        )
    }
}