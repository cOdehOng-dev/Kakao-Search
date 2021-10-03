package com.c0de_h0ng.kakaosearch.common.module

import android.content.Context
import com.c0de_h0ng.kakaosearch.api.ApiService
import com.c0de_h0ng.kakaosearch.common.Constants.BASE_URL
import com.c0de_h0ng.kakaosearch.common.Constants.CONNECT_TIMEOUT
import com.c0de_h0ng.kakaosearch.common.Constants.KAKAO_REST_API_KEY
import com.c0de_h0ng.kakaosearch.common.Constants.READ_TIMEOUT
import com.c0de_h0ng.kakaosearch.common.Constants.WRITE_TIMEOUT
import com.c0de_h0ng.kakaosearch.common.PrettyHttpLogging
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttp(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(PrettyHttpLogging())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().apply {
                    addHeader("Authorization", KAKAO_REST_API_KEY)
                }.build())
            }
            .addInterceptor(interceptor)
            .cache(cache)
            .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}