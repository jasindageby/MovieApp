package com.jasindagebyriani.movieapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jasindagebyriani.movieapp.databinding.ItemMovieBinding
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

class MovieViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieViewObject) {
        binding.apply {
            Glide.with(binding.root).load(movie.posterPath).into(ivMovie)
            tvTitle.text = movie.title
            tvGenre.text = movie.genre.toString()
        }
    }
}