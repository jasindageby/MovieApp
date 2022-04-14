package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.usecase.LoginUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {
    // password < 4 ->error
    // email -> email pattern

    private lateinit var useCase: LoginUseCase
    private lateinit var presenter: LoginContract.Presenter

    @Before
    fun setup(){
        useCase = mock()

        presenter = LoginPresenter(useCase)
    }

    @Test
    fun `given password less than 4 character when onLogin should return error`(){
        presenter.onLoginClick("username", "123")

        verify()
    }
}