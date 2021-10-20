package com.c0de_h0ng.kakaosearch.data.video

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/20.
 */
data class VideoModel(
    @SerializedName("thumbnail")
    val thumbnailUrl: String?,

    @SerializedName("datetime")
    val datetime: String?
)
