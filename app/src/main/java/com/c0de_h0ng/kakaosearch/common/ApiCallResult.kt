package com.c0de_h0ng.kakaosearch.common

/**
 * Created by c0de_h0ng on 2021/10/20.
 */
sealed class ApiCallResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ApiCallResult<T>()
    data class Error(val exception: String?, val errorType: Int) : ApiCallResult<Nothing>()
}
