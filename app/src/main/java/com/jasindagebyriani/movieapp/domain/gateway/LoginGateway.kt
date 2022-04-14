package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.LoginApi
import com.jasindagebyriani.movieapp.domain.api.response.LoginRequest
import com.jasindagebyriani.movieapp.domain.api.response.LoginResponse
import com.jasindagebyriani.movieapp.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface LoginGateway {
    fun onLogin(username: String, password: String): Single<User>
}

class LoginGatewayImpl(
    private val loginApi: LoginApi
) : LoginGateway {
    override fun onLogin(username: String, password: String): Single<User> {
        val request = LoginRequest(username, password)
        return loginApi.onLogin(request)
            .map { it.mapToUser() }
    }

    private fun LoginResponse.mapToUser(): User {
        return with(this) {
            User(id, username, avatar)
        }
    }
}