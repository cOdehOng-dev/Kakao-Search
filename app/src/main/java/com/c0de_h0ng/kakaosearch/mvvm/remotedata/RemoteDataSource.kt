package com.c0de_h0ng.kakaosearch.mvvm.remotedata

import com.c0de_h0ng.kakaosearch.api.ApiService
import com.c0de_h0ng.kakaosearch.data.blog.BlogModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class RemoteDataSource @Inject constructor(private val apiService: ApiService) : RemoteDataSourceInterface {

    override suspend fun blogSearch(searchWord: String, filter: String, page: Int, size: Int, onResponse: (Response<BlogModel>) -> Unit, onFailure: (Throwable) -> Unit) {
        apiService.blogSearch(searchWord, filter, page, size).enqueue(object : Callback<BlogModel> {
            override fun onResponse(call: Call<BlogModel>, response: Response<BlogModel>) {
                onResponse(response)
            }

            override fun onFailure(call: Call<BlogModel>, t: Throwable) {
                onFailure(t)
            }
        })
    }

}