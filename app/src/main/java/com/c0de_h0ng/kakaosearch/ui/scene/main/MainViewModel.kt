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

    private val _integrateBlogModel = MutableLiveData<BlogModel>()
    val integrateBlogModel: LiveData<BlogModel>
        get() = _integrateBlogModel

    private val _blogModel = MutableLiveData<BlogModel>()
    val blogModel: LiveData<BlogModel>
        get() = _blogModel

    private var blogPage = 1
    fun getBlogPage(): Int = blogPage

    private var isBlogEndPage = false
    fun isBlogEndPage(): Boolean = isBlogEndPage

    fun resetPageCount() {
        blogPage = 1

    }


    fun integrateBlogSearch(searchWord: String, filter: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.blogSearch(searchWord, filter, 1, 6,
                    onResponse = {
                        if (it.isSuccessful && it.body() != null) {
                            _integrateBlogModel.value = it.body()
                            isBlogEndPage = it.body()!!.page.isEnd
                        }
                    },
                    onFailure = {

                    }
                )
            }
        }
    }

    fun blogSearch(searchWord: String, filter: String, page: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.blogSearch(searchWord, filter, page, 20,
                    onResponse = {
                        if (it.isSuccessful && it.body() != null) {
                            _blogModel.value = it.body()
                            blogPage += 1
                        }
                    },
                    onFailure = {

                    }
                )
            }
        }
    }

}