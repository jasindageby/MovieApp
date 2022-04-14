package com.jasindagebyriani.movieapp.presenter

interface LoginContract {
    interface View {
        fun successLogin()
        fun errorLogin()
    }

    interface Presenter {
        fun attachView(view: View)
        fun onLoginClick(username: String, password: String)
        fun detachView()
    }
}