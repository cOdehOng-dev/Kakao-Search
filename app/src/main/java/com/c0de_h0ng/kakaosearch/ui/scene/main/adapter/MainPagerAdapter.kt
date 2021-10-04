package com.c0de_h0ng.kakaosearch.ui.scene.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.c0de_h0ng.kakaosearch.base.BaseActivity
import com.c0de_h0ng.kakaosearch.ui.scene.main.fragment.BlogFragment
import com.c0de_h0ng.kakaosearch.ui.scene.main.fragment.IntegrateFragment

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class MainPagerAdapter constructor(activity: BaseActivity<*>) : FragmentStateAdapter(activity) {

    private val mainContentFragmentList: ArrayList<Fragment> = ArrayList()

    init {
        mainContentFragmentList.add(IntegrateFragment())
        mainContentFragmentList.add(BlogFragment())
    }

    override fun getItemCount(): Int {
        return mainContentFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mainContentFragmentList[position]
    }

}