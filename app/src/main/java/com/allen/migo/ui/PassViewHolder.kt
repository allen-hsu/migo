package com.allen.migo.ui

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.allen.migo.R
import com.allen.migo.framework.BaseRecyclerViewHolder
import com.allen.migo.logic.Pass
import com.allen.migo.logic.PassStatus
import kotlinx.android.synthetic.main.item_pass.view.*

class PassViewHolder(
    parent: ViewGroup,
    private val onItemClicked: ((data: Pass) -> Unit)? = null,
    private val onPassBtnClicked: ((data: Pass) -> Unit)? = null,
    private var latestExpiredTime: LiveData<Long>? = null
) : BaseRecyclerViewHolder<Pass>(R.layout.item_pass, parent) {

    private var pass: Pass? = null
    private val observer = Observer<Long> {
        val currentTimestamp = System.currentTimeMillis()
        itemView.btn_activate.isEnabled = it <= 0 || currentTimestamp > it
    }
    init {
        itemView.apply {
            setOnClickListener {
                pass?.apply {
                    onItemClicked?.invoke(this)
                }
            }
            this.btn_activate.setOnClickListener {
                pass?.apply {
                    onPassBtnClicked?.invoke(this)
                }
            }
        }
    }

    override fun bind(data: Pass) {
        this.pass = data

        itemView.text_pass_type.apply {
            text = itemView.context.getString(R.string.text_pass_type, data.unitNum(), data.passType().toString())
        }

        itemView.text_status.apply {
            text =
                itemView.context.getString(R.string.text_pass_status, data.passStatus().toString())
        }

        itemView.btn_activate.apply {
            visibility = if (data.passStatus() == PassStatus.NOT_ACTIVATE) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    override fun initObserve() {
        latestExpiredTime?.observeForever(observer)
    }

    override fun releaseObserve() {
        latestExpiredTime?.removeObserver(observer)
        latestExpiredTime = null
    }
}