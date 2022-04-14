package com.jasindagebyriani.movieapp.domain.usecase

import com.jasindagebyriani.movieapp.domain.entity.User
import com.jasindagebyriani.movieapp.domain.gateway.LoginGateway
import io.reactivex.rxjava3.core.Single

interface LoginUseCase {
    fun onLogin(username: String, password: String): Single<User>
}

class LoginUseCaseImpl(
    private val gateway: LoginGateway
) : LoginUseCase {
    override fun onLogin(username: String, password: String): Single<User> {
        return gateway.onLogin(username, password)
    }
}