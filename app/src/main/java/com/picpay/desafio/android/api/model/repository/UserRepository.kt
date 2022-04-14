package com.picpay.desafio.android.api.model.repository

import com.picpay.desafio.android.api.model.User

interface UserRepository {
    suspend fun getUsersListRemote() : List<User>
}