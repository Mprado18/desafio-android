package com.picpay.desafio.android.di

import com.picpay.desafio.android.interactor.GetUsersInteractor
import com.picpay.desafio.android.ui.users_list_activity.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        GetUsersInteractor()
    }

    viewModel { (interactor: GetUsersInteractor) ->
        UsersViewModel(interactor)
    }
}

