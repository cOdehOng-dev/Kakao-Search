package com.c0de_h0ng.kakaosearch.data.page

import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
data class Page(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("pageable_count")
    val pageableCount: Long,
    @SerializedName("is_end")
    val isEnd: Boolean
)
