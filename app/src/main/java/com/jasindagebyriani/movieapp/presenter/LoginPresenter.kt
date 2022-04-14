package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.domain.usecase.LoginUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginPresenter(
    private val loginUseCase: LoginUseCase,
    private val ioScheduler: Scheduler = Schedulers.io(),
    private val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View
    private val disposable = CompositeDisposable()

    override fun onLoginClick(username: String, password: String) {
        val isUseMatch = android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
        if (!isUseMatch) {
            view.errorLogin()
            return
        }
        if (password.length < MIN_PASSWORD) {
            view.errorLogin()
            return
        }

        loginUseCase.onLogin(username, password)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                view.successLogin()
            }, {
                view.errorLogin()
            }).let(disposable::add)

    }

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        disposable.clear()
    }

    companion object {
        private const val MIN_PASSWORD = 4
    }
}