package com.jasindagebyriani.movieapp.presenter

import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

interface MovieDetailContract {
    interface Presenter {
        fun clickFavorite(movieViewObject: MovieViewObject)
    }
}