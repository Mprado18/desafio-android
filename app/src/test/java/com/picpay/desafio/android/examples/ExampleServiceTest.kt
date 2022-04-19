package com.picpay.desafio.android.examples

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.api.model.User
import com.picpay.desafio.android.api.retrofit.service.PicPayService
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

//    @Test
//    suspend fun exampleTest() {
//        // given
//        val call = mock<List<User>>()
//        val expectedUsers = emptyList<User>()
//
//        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
//        whenever(api.getUsers()).thenReturn(call)
//
//        // when
//        val users = service.example()
//
//        // then
//        assertEquals(users, expectedUsers)
//    }
}