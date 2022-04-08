package com.jasindagebyriani.movieapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jasindagebyriani.movieapp.databinding.ItemMovieBinding
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject

class MovieAdapter(
    private val onClickFavorite: (MovieViewObject) -> Unit
) : ListAdapter<MovieViewObject, MovieViewHolder>(MovieDiffUtil()) {

    private val list = mutableListOf<MovieViewObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getListItem(position))

        holder.binding.ivFavorite.setOnClickListener {
            list[position] = list[position].copy(isFavorite = !list[position].isFavorite)
            onClickFavorite(list[position])
            notifyItemChanged(position)
        }
    }

    private fun getListItem(position: Int): MovieViewObject {
        val contain = list.firstOrNull { it.id == getItemId(position) } != null
        if (contain) list[position] = getItem(position)
        else list.add(getItem(position))

        return list[position]
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