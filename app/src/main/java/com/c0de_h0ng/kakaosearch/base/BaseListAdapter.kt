package com.c0de_h0ng.kakaosearch.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
abstract class BaseListAdapter<T : BaseListHolder<*, *>?, S>(arrayList: ArrayList<S>?) : RecyclerView.Adapter<T>() {

    val arrayList: ArrayList<S> = ArrayList()
    var recyclerView: RecyclerView? = null
    private var lastPosition = 0

    init {
        if (arrayList != null && arrayList.isNotEmpty()) {
            this.arrayList.addAll(arrayList)
        }
    }

    private fun changeListData(arrayList: ArrayList<S>?) {
        this.arrayList.clear()
        arrayList?.let { addListData(it) }
    }

    fun setListData(arrayList: ArrayList<S>?) {
        changeListData(arrayList)
        notifyDataSetChanged()
    }

    fun addData(arrayList: ArrayList<S>) {
        val currentIndex = this.arrayList.size
        this.arrayList.addAll(arrayList)
        notifyItemRangeInserted(currentIndex, this.arrayList.size - 1)
    }

    fun clear() {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun refreshList(refreshList: ArrayList<S>?, isScrollRefresh: Boolean) {
        clear()
        setListData(refreshList)
        if (isScrollRefresh) {
            try {
                recyclerView?.scrollToPosition(0) //스크롤 초기화
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun addListData(arrayList: ArrayList<S>) {
        this.arrayList.addAll(arrayList)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun getPosition(v: View): Int {
        val holder = getViewHolder(v)
        return holder?.bindingAdapterPosition ?: -1
    }

    fun getViewDataBinding(v: View): ViewDataBinding? {
        val viewHolder = getViewHolder(v) as BaseListHolder<*, *>?
        return viewHolder?.dataBinding
    }

    private fun getViewHolder(v: View): RecyclerView.ViewHolder? {
        return recyclerView?.findContainingViewHolder(v)
    }

}