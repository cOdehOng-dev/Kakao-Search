package com.c0de_h0ng.kakaosearch.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
object BindingAdapter {

    @BindingAdapter(value = ["tab_list", "view_pager", "pager_adapter"])
    @JvmStatic
    fun bindTabWithViewPager(
        tabLayout: TabLayout,
        tabList: Array<String>,
        viewPager2: ViewPager2?,
        adapter: FragmentStateAdapter?
    ) {
        if (adapter != null && viewPager2 != null) {
            viewPager2.isSaveEnabled = false
            viewPager2.adapter = adapter
            try {
                viewPager2.offscreenPageLimit = tabList.size
            } catch (e: Exception) {
                e.printStackTrace()
            }
            viewPager2.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = tabList[position]
            }.attach()
        }
    }

}