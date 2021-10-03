package com.c0de_h0ng.kakaosearch.data.blog

import com.c0de_h0ng.kakaosearch.data.page.Page
import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
data class BlogModel(
    @SerializedName("meta")
    val page: Page,
    @SerializedName("documents")
    val blogContentList: ArrayList<BlogContent>
)