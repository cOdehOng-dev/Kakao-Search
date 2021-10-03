package com.c0de_h0ng.kakaosearch.ui.scene.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseFragment
import com.c0de_h0ng.kakaosearch.databinding.BlogFragmentBinding
import com.c0de_h0ng.kakaosearch.ui.scene.main.MainViewModel

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class BlogFragment : BaseFragment<BlogFragmentBinding>(R.layout.blog_fragment) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun observeViewModel() {

    }



}