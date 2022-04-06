package com.jasindagebyriani.movieapp.presenter

interface MainContract {
    interface Presenter{
        fun attachView(view: View)
        fun loadTotalFavorite()
        fun detachView()
    }

    interface View {
        fun showTotalFavorite(total: Int)
    }
}