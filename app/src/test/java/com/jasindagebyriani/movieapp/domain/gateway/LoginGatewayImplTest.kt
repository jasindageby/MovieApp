package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.LoginApi
import com.jasindagebyriani.movieapp.domain.entity.User
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class LoginGatewayImplTest {
    @get:Rule
    val server = MockWebServer()

    private lateinit var gateway: LoginGateway

    @Before
    fun setUp() {
        gateway = LoginGatewayImpl(createApi())
    }

    @Test
    fun `given username and password when onLogin success should return user data`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(response))

        val expectedUser = User(
            3,
            "username",
            "https://cdn.fakercloud.com/avatars/snowwrite_128.jpg"
        )

        gateway.onLogin("username", "password")
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue(expectedUser)
            .assertNoErrors()

    }

    private fun createApi(): LoginApi {
        return Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    companion object {
        private val response = """
            {
                "id": "3",
                "username": "username",
                "avatar": "https://cdn.fakercloud.com/avatars/snowwrite_128.jpg",
            }
        """.trimIndent()
    }
}