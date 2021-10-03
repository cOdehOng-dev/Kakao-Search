package com.c0de_h0ng.kakaosearch.common

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseListAdapter
import com.c0de_h0ng.kakaosearch.common.recyclerview.RecyclerItemListener
import com.c0de_h0ng.kakaosearch.common.recyclerview.RecyclerTouchListener
import com.c0de_h0ng.kakaosearch.ui.component.IntegrateSearchContent
import com.c0de_h0ng.kakaosearch.ui.component.RadioContent
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

    @BindingAdapter(value = ["list_adapter", "touch"], requireAll = false)
    @JvmStatic
    fun bindListAdapter(view: RecyclerView, listAdapter: BaseListAdapter<*, *>?, recyclerTouchListener: RecyclerTouchListener?) {
        listAdapter?.let { view.adapter = it }
        recyclerTouchListener?.let { view.addOnItemTouchListener(RecyclerItemListener(view, it)) }
    }

    @BindingAdapter("radio_checked_changed_listener")
    @JvmStatic
    fun bindRadioCheckedChangedListener(radioContent: RadioContent, onCheckedChangeListener: RadioGroup.OnCheckedChangeListener?) {
        onCheckedChangeListener?.let {
            radioContent.setOnCheckedChangedListener(it)
        }
    }

    @BindingAdapter(value = ["integrate_search_content_list_adapter", "integrate_search_content_list_touch", "integrate_search_content_click_listener"], requireAll = false)
    @JvmStatic
    fun bindIntegrateSearchContentListener(
        integrateSearchContent: IntegrateSearchContent,
        listAdapter: BaseListAdapter<*, *>?,
        recyclerTouchListener: RecyclerTouchListener?,
        onClickListener: View.OnClickListener?
    ) {
        listAdapter?.let {
            integrateSearchContent.contentRecyclerView.addItemDecoration(CustomDecoration(Utility.dpToPx(1).toFloat(), Utility.dpToPx(10).toFloat(), ContextCompat.getColor(integrateSearchContent.context, R.color.gray_eb)))
            integrateSearchContent.contentRecyclerView.adapter = it
        }
        recyclerTouchListener?.let {
            integrateSearchContent.contentRecyclerView.addOnItemTouchListener(RecyclerItemListener(integrateSearchContent.contentRecyclerView, it))
        }
        onClickListener?.let {
            integrateSearchContent.moreButton.setOnClickListener(it)
        }
    }

    @BindingAdapter(value = ["glide_radius_img_url", "corner_radius"], requireAll = false)
    @JvmStatic
    fun bindGlideRadiusImgUrl(imageView: ImageView, url: String?, radius: Int) {
        if (url == null) return
        val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(Utility.dpToPx(radius)))
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.color.gray_f_1)
            .apply(requestOptions)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    imageView.visibility = GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(imageView)
    }

}