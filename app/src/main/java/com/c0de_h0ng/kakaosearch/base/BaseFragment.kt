package com.c0de_h0ng.kakaosearch.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
abstract class BaseFragment<T : ViewDataBinding> constructor(@LayoutRes private val layoutResId: Int) : Fragment(), View.OnClickListener {

    lateinit var rootActivity: BaseActivity<*>

    lateinit var binding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        observeViewModel()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun observeViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) rootActivity = context
    }

    override fun onClick(v: View) {
        rootActivity.onClick(v)
    }

}