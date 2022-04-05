package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

interface PopularContract {
    interface Presenter {
        fun attachView(view: View)
        fun loadData()
        fun detachView()
    }

    interface View {
        fun showList(movies: List<MovieViewObject>)
        fun showError()
    }
}