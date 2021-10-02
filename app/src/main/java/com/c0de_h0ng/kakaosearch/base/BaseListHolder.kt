package com.c0de_h0ng.kakaosearch.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
abstract class BaseListHolder<T : ViewDataBinding?, S>(val dataBinding: T?) : RecyclerView.ViewHolder(dataBinding!!.root) {

    fun bind(data: S?) {
        setDataBindingWithData(data)
    }

    abstract fun setDataBindingWithData(data: S?)
}