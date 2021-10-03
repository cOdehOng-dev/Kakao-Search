package com.c0de_h0ng.kakaosearch.ui.scene.main

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseActivity
import com.c0de_h0ng.kakaosearch.common.Constants.ACCURACY
import com.c0de_h0ng.kakaosearch.common.Constants.RECENCY
import com.c0de_h0ng.kakaosearch.common.eventbus.EventBusProvider
import com.c0de_h0ng.kakaosearch.common.eventbus.SelectMoreButtonEvent
import com.c0de_h0ng.kakaosearch.databinding.ActivityMainBinding
import com.c0de_h0ng.kakaosearch.ui.scene.main.adapter.MainPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.squareup.otto.Subscribe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), RadioGroup.OnCheckedChangeListener, TextView.OnEditorActionListener  {

    private val viewModel: MainViewModel by viewModels()

    override fun bindingProperty() {
        binding.onCheckedChangedListener = this
        binding.onEditorActionListener = this
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
        EventBusProvider.getInstance().register(this)
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBusProvider.getInstance().unregister(this)
    }

    @Subscribe
    fun selectMoreButtonEvent(event: SelectMoreButtonEvent) {
        if (event.selectMoreButtonTag > 0) {
            tabScrollToPosition(binding.mainTab, binding.mainViewPager, event.selectMoreButtonTag)
        }
    }

    override fun onClick(v: View) {

    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.accuracy_radio_button -> {
                Log.d("filter", "정확도순")
                viewModel.filter.value = ACCURACY
            }
            R.id.recent_radio_button -> {
                Log.d("filter", "최신순")
                viewModel.filter.value = RECENCY
            }
        }
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (v.id == R.id.et_search_word && actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.searchWord.value = binding.etSearchWord.text.toString()
            hideKeyboard()
        }
        return false
    }

    private fun observeViewModel() {
        viewModel.searchWord.observe(this) {
            if (it.isEmpty()) {
                binding.tabContent.visibility = GONE
                binding.searchContent.visibility = GONE
            } else {
                binding.tabContent.visibility = VISIBLE
                binding.searchContent.visibility = VISIBLE
            }
        }
    }

    private fun tabScrollToPosition(tab: TabLayout, viewPager: ViewPager2, index: Int) {
        tab.setScrollPosition(index, 0f, true)
        viewPager.setCurrentItem(index, false)
    }


}