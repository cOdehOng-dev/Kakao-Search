package com.c0de_h0ng.kakaosearch.ui.scene.main.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.c0de_h0ng.kakaosearch.R
import com.c0de_h0ng.kakaosearch.base.BaseListAdapter
import com.c0de_h0ng.kakaosearch.base.BaseListHolder
import com.c0de_h0ng.kakaosearch.data.blog.BlogContent
import com.c0de_h0ng.kakaosearch.databinding.BlogListItemBinding

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class BlogListAdapter constructor(blogList: ArrayList<BlogContent>, private val isMain: Boolean = false) : BaseListAdapter<BaseListHolder<BlogListItemBinding, BlogContent>, BlogContent>(blogList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder<BlogListItemBinding, BlogContent> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.blog_list_item, parent, false)
        val binding: BlogListItemBinding? = DataBindingUtil.bind(view)
        return object : BaseListHolder<BlogListItemBinding, BlogContent>(binding) {
            override fun setDataBindingWithData(data: BlogContent?) {
                dataBinding?.let {
                    it.blogContent = data
                    it.tvTitle.text = Html.fromHtml(data?.title)
                    it.tvContent.text = Html.fromHtml(data?.contents)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseListHolder<BlogListItemBinding, BlogContent>, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return if (isMain && super.getItemCount() > 5) {
            5
        } else {
            super.getItemCount()
        }
    }

}