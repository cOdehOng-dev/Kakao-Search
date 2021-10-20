package com.c0de_h0ng.kakaosearch.data.image

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/20.
 */
data class ImageContent(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,

    @SerializedName("datetime")
    val datetime: String?
)
