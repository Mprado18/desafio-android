package com.picpay.desafio.android.ui.users_list_activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.api.model.User
import com.picpay.desafio.android.interactor.GetUsersInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val getUsersInteractor: GetUsersInteractor
) : ViewModel() {

    private val usersMutableLiveData = MutableLiveData<UsersCommand>()
    val mUsersMutableLiveData = usersMutableLiveData

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = getUsersInteractor.getUsersListRemote()
                usersMutableLiveData.value = UsersCommand.RenderUsers(response)
            } catch (exception: Exception) {
                usersMutableLiveData.value = UsersCommand.ShowRequestError(true)
            }
        }
    }

}

sealed class UsersCommand {
    data class RenderUsers(val userList: List<User>) : UsersCommand()
    data class ShowRequestError(val error: Boolean) : UsersCommand()
}