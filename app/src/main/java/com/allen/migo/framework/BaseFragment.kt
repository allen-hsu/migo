package com.allen.migo.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun init(view: View)
    abstract fun release(view: View)

    @LayoutRes
    abstract fun getLayout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(getLayout(), container, false)
        init(root)
        return root
    }

    override fun onDestroyView() {
        view?.run {
            release(this)
        }
        super.onDestroyView()
    }

    open fun onBackPressed(): Boolean {
        // Return false means let activity control the.onBackPressed logic,
        // return true means let fragment control the.onBackPressed logic by itself.
        return false
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }
}