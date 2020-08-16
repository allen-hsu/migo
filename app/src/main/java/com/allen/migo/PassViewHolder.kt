package com.allen.migo

import android.view.View
import android.view.ViewGroup
import com.allen.migo.logic.Pass
import com.allen.migo.logic.PassStatus
import kotlinx.android.synthetic.main.item_pass.view.*

class PassViewHolder(
    parent: ViewGroup,
    private val onItemClicked: ((data: Pass) -> Unit)? = null,
    private val onPassBtnClicked: ((data: Pass) -> Unit)? = null
) : BaseRecyclerViewHolder<Pass>(R.layout.item_pass, parent) {

    private var pass: Pass? = null

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
            text = itemView.context.getString(R.string.text_pass_type, data.passType().toString())
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
}