package com.c0de_h0ng.kakaosearch.ui.scene.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseFragment
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
        //blogApiCall()
        return binding.root
    }

    override fun observeViewModel() {
        viewModel.mainBlogModel.observe(this) {
            binding.blogListAdapter?.refreshList(it.blogContentList, true)
                ?: run {
                    binding.blogListAdapter = BlogListAdapter(it.blogContentList)
                }
        }

        viewModel.filter.observe(this) {
            viewModel.blogSearch("박스터", viewModel.filter.value!!, 1, 30)
        }

    }


    private fun blogApiCall() {
        viewModel.blogSearch("박스터", "accuracy", 1, 30)
    }


}