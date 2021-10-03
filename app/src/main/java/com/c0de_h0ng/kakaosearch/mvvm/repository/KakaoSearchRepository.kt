package com.c0de_h0ng.kakaosearch.mvvm.repository

import com.c0de_h0ng.kakaosearch.data.blog.BlogModel
import com.c0de_h0ng.kakaosearch.mvvm.remotedata.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class KakaoSearchRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun blogSearch(searchWord: String, filter: String, page: Int, size: Int, onResponse: (Response<BlogModel>) -> Unit, onFailure: (Throwable) -> Unit) {
        remoteDataSource.blogSearch(searchWord, filter, page, size, onResponse, onFailure)
    }

}