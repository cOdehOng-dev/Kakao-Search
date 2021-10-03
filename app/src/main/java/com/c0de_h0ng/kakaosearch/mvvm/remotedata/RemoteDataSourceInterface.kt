package com.c0de_h0ng.kakaosearch.mvvm.remotedata

import com.c0de_h0ng.kakaosearch.data.blog.BlogModel
import retrofit2.Response
import retrofit2.http.Query

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
interface RemoteDataSourceInterface {

    suspend fun blogSearch(
        @Query("query") searchWord: String,
        @Query("sort") filter: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        onResponse: (Response<BlogModel>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}