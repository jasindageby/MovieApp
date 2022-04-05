package com.jasindagebyriani.movieapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jasindagebyriani.movieapp.databinding.ItemMovieBinding
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

class MovieAdapter : ListAdapter<MovieViewObject, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<MovieViewObject>() {
    override fun areItemsTheSame(oldItem: MovieViewObject, newItem: MovieViewObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieViewObject, newItem: MovieViewObject): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}