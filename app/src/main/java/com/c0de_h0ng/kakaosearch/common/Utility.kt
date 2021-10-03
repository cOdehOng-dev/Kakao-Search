package com.c0de_h0ng.kakaosearch.common

import android.content.res.Resources

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
object Utility {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}