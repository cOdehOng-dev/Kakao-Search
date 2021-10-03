package com.c0de_h0ng.kakaosearch.data.blog

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
data class BlogContent(
    @SerializedName("title")
    val title: String,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("blogname")
    val blogName: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("datetime")
    val datetime: String
)
