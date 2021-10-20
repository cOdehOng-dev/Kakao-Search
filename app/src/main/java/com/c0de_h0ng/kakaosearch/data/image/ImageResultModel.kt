package com.c0de_h0ng.kakaosearch.data.image

import com.c0de_h0ng.kakaosearch.data.page.Page
import com.google.gson.annotations.SerializedName

/**
 * Created by c0de_h0ng on 2021/10/20.
 */
data class ImageResultModel(
    @SerializedName("meta")
    val page: Page,

    @SerializedName("documents")
    val imageResultList: List<ImageContent>
)
