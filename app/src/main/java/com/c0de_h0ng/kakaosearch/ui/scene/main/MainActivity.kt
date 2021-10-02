package com.c0de_h0ng.kakaosearch.ui.scene.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseActivity
import com.c0de_h0ng.kakaosearch.databinding.ActivityMainBinding
import com.c0de_h0ng.kakaosearch.ui.scene.main.adapter.MainPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun bindingProperty() {
        binding.tabList = resources.getStringArray(R.array.main_tab)
        binding.viewPager = binding.mainViewPager
        binding.pagerAdapter = MainPagerAdapter(this)

        binding.mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabLayout: LinearLayout = (binding.mainTab.getChildAt(0) as ViewGroup).getChildAt(tab?.position ?: 0) as LinearLayout
                val tabTextView: TextView = tabLayout.getChildAt(1) as TextView
                setFont(R.font.kakao_bold, tabTextView)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabLayout: LinearLayout = (binding.mainTab.getChildAt(0) as ViewGroup).getChildAt(tab?.position ?: 0) as LinearLayout
                val tabTextView: TextView = tabLayout.getChildAt(1) as TextView
                setFont(R.font.kakao_regular, tabTextView)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {

    }

}