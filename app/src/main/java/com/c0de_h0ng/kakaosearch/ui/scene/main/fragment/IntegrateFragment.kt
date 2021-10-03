package com.c0de_h0ng.kakaosearch.ui.scene.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseFragment
import com.c0de_h0ng.kakaosearch.common.Constants.BLOG_MORE
import com.c0de_h0ng.kakaosearch.common.eventbus.EventBusProvider
import com.c0de_h0ng.kakaosearch.common.eventbus.SelectMoreButtonEvent
import com.c0de_h0ng.kakaosearch.databinding.IntegrateFragmentBinding
import com.c0de_h0ng.kakaosearch.ui.scene.main.MainViewModel
import com.c0de_h0ng.kakaosearch.ui.scene.main.adapter.BlogListAdapter

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class IntegrateFragment : BaseFragment<IntegrateFragmentBinding>(R.layout.integrate_fragment) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.onClick = this
        return binding.root
    }

    override fun observeViewModel() {
        viewModel.mainBlogModel.observe(this) {
            if (it.blogContentList.isNotEmpty()) {
                binding.blogListAdapter?.refreshList(it.blogContentList, true)
                    ?: run {
                        binding.blogListAdapter = BlogListAdapter(it.blogContentList, true)
                    }
                if (it.blogContentList.size > 5) {
                    binding.blogContent.moreButton.visibility = VISIBLE
                } else {
                    binding.blogContent.moreButton.visibility = GONE
                }
                binding.blogContent.visibility = VISIBLE
            } else {
                binding.blogContent.visibility = GONE
            }

        }

        viewModel.searchWord.observe(this) {
            if (it.isNotEmpty()) {
                integrateApiCall(viewModel.searchWord.value!!, it)
            }
        }

        viewModel.filter.observe(this) {
            if (viewModel.searchWord.value!!.isNotEmpty()) {
                integrateApiCall(viewModel.searchWord.value!!, it)
            }

        }
    }

    private fun integrateApiCall(searchWord: String, filter: String) {
        viewModel.blogSearch(searchWord, filter, 1, 6) //블로그 호출
    }

    override fun onClick(v: View) {
        if (v.id == R.id.more_button) {
            when (v.tag) {
                BLOG_MORE -> {
                    binding.integrateScrollView.scrollTo(0, 0)
                    EventBusProvider.getInstance().post(SelectMoreButtonEvent(BLOG_MORE))
                }
            }
        }
    }


}