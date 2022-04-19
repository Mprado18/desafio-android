package com.picpay.desafio.android.ui.users_list_activity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.api.model.User
import com.picpay.desafio.android.api.model.repository.UserRepository
import com.picpay.desafio.android.api.retrofit.service.PicPayService
import com.picpay.desafio.android.interactor.GetUsersInteractor
import com.picpay.desafio.android.util.TestCoroutineRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UsersViewModel

    @Mock
    private val api = mock<PicPayService>()

    @Mock
    private lateinit var interactor: GetUsersInteractor

    @Before
    fun setup() {
        viewModel = UsersViewModel(interactor)
    }

    //entender melhor como estruturar este erro
    @Test
    fun `should be get user in api`() {
        testCoroutineRule.runBlockingTest {
            //given
            val user = mock<List<User>>()
            val usersList = emptyList<User>()

            whenever(user).thenReturn(usersList)
            whenever(api.getUsers()).thenReturn(usersList)

            //when
            viewModel.getUsers()

            //then
            assertEquals(user, usersList)
        }
    }

    //entender melhor como estruturar este erro
    @Test
    fun `should be return error in request user list of api`() {
        testCoroutineRule.runBlockingTest {
            //given
            val error = UsersCommand.ShowRequestError(true)
            val usersMutableLiveData = MutableLiveData<UsersCommand>()

            doReturn(error).`when`(interactor).getUsersListRemote()

            //when
            viewModel.getUsers()

            //then
            assertEquals(error, usersMutableLiveData.value)
        }
    }
}