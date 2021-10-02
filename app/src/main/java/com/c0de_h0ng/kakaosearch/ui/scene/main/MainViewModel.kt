package com.c0de_h0ng.kakaosearch.ui.scene.main

import androidx.lifecycle.ViewModel
import com.c0de_h0ng.kakaosearch.mvvm.repository.KakaoSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: KakaoSearchRepository) : ViewModel() {


}