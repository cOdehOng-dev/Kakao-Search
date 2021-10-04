package com.c0de_h0ng.kakaosearch.common

import android.content.res.Resources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.c0de_h0ng.kakaosearch.common.recyclerview.RecyclerViewScrollDetectListener

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
object Utility {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun onRecyclerViewScrollListener(detectListenerRecyclerView: RecyclerViewScrollDetectListener): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                try {
                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    recyclerView.adapter?.let {
                        val itemTotalCount = it.itemCount - 1
                        if (lastVisibleItemPosition == itemTotalCount) {
                            detectListenerRecyclerView.scrollDetectLastItem()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}