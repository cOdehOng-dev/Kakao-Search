package com.c0de_h0ng.kakaosearch.ui.scene.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.kakaosearch.common.Constants.ACCURACY
import com.c0de_h0ng.kakaosearch.data.blog.BlogModel
import com.c0de_h0ng.kakaosearch.mvvm.repository.KakaoSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: KakaoSearchRepository) : ViewModel() {

    val searchWord = MutableLiveData("")
    val filter = MutableLiveData(ACCURACY)

    //블로그
    private val _mainBlogModel = MutableLiveData<BlogModel>()
    val mainBlogModel: LiveData<BlogModel>
        get() = _mainBlogModel

    fun blogSearch(searchWord: String, filter: String, page: Int, size: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.blogSearch(searchWord, filter, page, size,
                    onResponse = {
                        if (it.isSuccessful && it.body() != null) {
                            _mainBlogModel.value = it.body()
                        }
                    },
                    onFailure = {

                    }
                )
            }
        }
    }

}