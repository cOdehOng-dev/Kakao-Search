package com.c0de_h0ng.kakaosearch.ui.scene.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseFragment
import com.c0de_h0ng.kakaosearch.common.Utility
import com.c0de_h0ng.kakaosearch.common.eventbus.EventBusProvider
import com.c0de_h0ng.kakaosearch.common.eventbus.ResetContentEvent
import com.c0de_h0ng.kakaosearch.common.recyclerview.RecyclerTouchListener
import com.c0de_h0ng.kakaosearch.common.recyclerview.RecyclerViewScrollDetectListener
import com.c0de_h0ng.kakaosearch.databinding.BlogFragmentBinding
import com.c0de_h0ng.kakaosearch.ui.scene.main.MainViewModel
import com.c0de_h0ng.kakaosearch.ui.scene.main.adapter.BlogListAdapter
import com.squareup.otto.Subscribe

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class BlogFragment : BaseFragment<BlogFragmentBinding>(R.layout.blog_fragment), RecyclerTouchListener, RecyclerViewScrollDetectListener {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBusProvider.getInstance().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.listTouch = this
        binding.blogListView.addOnScrollListener(Utility.onRecyclerViewScrollListener(this))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBusProvider.getInstance().unregister(this)
    }

    override fun observeViewModel() {
        viewModel.blogModel.observe(this) {
            if (viewModel.getBlogPage() == 1) {
                if (it.blogContentList.isNotEmpty()) {
                    binding.blogListAdapter?.refreshList(it.blogContentList, true)
                        ?: run { binding.blogListAdapter = BlogListAdapter(it.blogContentList, false) }
                } else {
                    binding.blogListAdapter?.clear()
                }
            } else {
                if (it.blogContentList.isNotEmpty()) {
                    binding.blogListAdapter?.addData(it.blogContentList)
                }
            }
        }

        viewModel.searchWord.observe(this) {
            if (it.isNotEmpty() && !viewModel.isBlogEndPage()) {
                viewModel.blogSearch(it, viewModel.filter.value!!, viewModel.getBlogPage())
            }
        }

        viewModel.filter.observe(this) {
            if (viewModel.searchWord.value!!.isNotEmpty() && !viewModel.isBlogEndPage()) {
                viewModel.blogSearch(viewModel.searchWord.value!!, it, viewModel.getBlogPage())
            }
        }
    }

    override fun onClickItem(v: View, position: Int) {
        if (v.id == R.id.blog_item) {
            val adapter = binding.blogListAdapter
            val item = adapter.arrayList[position]
        }
    }

    override fun scrollDetectLastItem() {
        viewModel.blogSearch(viewModel.searchWord.value!!, viewModel.filter.value!!, viewModel.getBlogPage())
    }

    @Subscribe
    fun resetContentEvent(event: ResetContentEvent) {
        binding.blogListAdapter?.clear()
    }

}