package com.c0de_h0ng.kakaosearch.common.recyclerview

import android.view.View

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
interface RecyclerTouchListener {
    fun onClickItem(v: View, position: Int)
}