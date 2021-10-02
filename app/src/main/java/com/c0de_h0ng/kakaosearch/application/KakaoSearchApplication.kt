package com.c0de_h0ng.kakaosearch.application

import android.app.Application
import com.c0de_h0ng.kakaosearch.common.Constants.KAKAO_APP_KEY
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
@HiltAndroidApp
class KakaoSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, KAKAO_APP_KEY)
    }
}