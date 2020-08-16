package com.allen.migo.ui.fragment

import android.os.Bundle
import android.view.View
import com.allen.migo.R
import com.allen.migo.framework.BaseFragment
import com.allen.migo.logic.PassParcelize
import com.allen.migo.logic.PassStatus
import kotlinx.android.synthetic.main.fragment_pass_detail.view.*

class PassDetailFragment : BaseFragment() {

    private var passParcelize: PassParcelize? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passParcelize = arguments?.getParcelable(BUNDLE_PASS_PARCELIZE)
    }
    override fun getLayout(): Int {
        return R.layout.fragment_pass_detail
    }

    override fun init(view: View) {

        passParcelize?.apply {

            view.text_pass_type.text =
                getString(R.string.text_pass_type, this.passUnitNum, this.passType)

            view.text_pass_status.text =
                getString(R.string.text_pass_status, this.passStatus)
            view.text_pass_insert_datetime.text =
                getString(R.string.text_pass_insert_data_time, this.insertDateTime)

            if (this.passStatus == PassStatus.EXPIRE || this.passStatus == PassStatus.ACTIVATE) {
                view.text_pass_activate_datetime.text =
                    getString(R.string.text_pass_activate_data_time, this.activateDateTime)
                view.text_pass_expired_datetime.text =
                    getString(R.string.text_pass_expired_data_time, this.expiredDateTime)
            } else {
                view.text_pass_activate_datetime.text = getString(
                    R.string.text_pass_activate_data_time,
                    PassStatus.NOT_ACTIVATE.toString()
                )
                view.text_pass_expired_datetime.text = getString(
                    R.string.text_pass_expired_data_time,
                    PassStatus.NOT_ACTIVATE.toString()
                )
            }
        }
    }

    override fun release(view: View) {

    }

    companion object {
        private const val BUNDLE_PASS_PARCELIZE = "pass_parcelize"
        fun newInstance(passParcelize: PassParcelize) = PassDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_PASS_PARCELIZE, passParcelize)
            }
        }
    }
}