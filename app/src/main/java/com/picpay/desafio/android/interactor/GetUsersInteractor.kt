package com.picpay.desafio.android.interactor

import com.picpay.desafio.android.api.model.repository.UserRepository
import com.picpay.desafio.android.api.retrofit.AppRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsersInteractor : UserRepository {

    override suspend fun getUsersListRemote() = withContext(Dispatchers.IO) {
        AppRetrofit().serviceApi.getUsers()
    }

}