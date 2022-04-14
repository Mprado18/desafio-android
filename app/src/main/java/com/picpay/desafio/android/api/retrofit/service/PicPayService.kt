package com.picpay.desafio.android.api.retrofit.service

import com.picpay.desafio.android.api.model.User
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>

}