package com.c0de_h0ng.kakaosearch.mvvm.repository

import com.c0de_h0ng.kakaosearch.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
class KakaoSearchRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getImageSearchResultList(
        searchWord: String,
        page: Int,
        size: Int
    ) = withContext(Dispatchers.IO) {
        return@withContext apiService.imageSearch(searchWord, "recency", page, size)
    }

}