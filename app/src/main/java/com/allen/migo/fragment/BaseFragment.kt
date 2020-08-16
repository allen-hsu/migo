package com.allen.migo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.allen.migo.activity.BaseActivity

abstract class BaseFragment : Fragment() {

    abstract fun init(view: View)

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

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }
}