package com.c0de_h0ng.kakaosearch.data.cafe

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/05.
 */
data class CafeContent(
    @SerializedName("title")
    val title: String,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("cafename")
    val cafeName: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("datetime")
    val datetime: String
)
