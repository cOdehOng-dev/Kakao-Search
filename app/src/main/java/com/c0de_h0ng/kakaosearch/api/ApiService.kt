package com.c0de_h0ng.kakaosearch.api

import com.c0de_h0ng.kakaosearch.data.blog.BlogModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
interface ApiService {

    @GET("v2/search/blog")
    fun blogSearch(
        @Query("query") searchWord: String,
        @Query("sort") filter: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Call<BlogModel>

}