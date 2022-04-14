package com.picpay.desafio.android.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.api.retrofit.service.PicPayService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val serviceApi: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }

}