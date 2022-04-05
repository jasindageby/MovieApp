package com.jasindagebyriani.movieapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jasindagebyriani.movieapp.databinding.ActivityMovieDetailBinding
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraData = intent.getParcelableExtra(EXTRA_MOVIE_VIEW_OBJECT) as MovieViewObject?
        extraData?.let {
            setupData(it)
        }

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun setupData(movieViewObject: MovieViewObject) {
        binding.apply {
            tvTitle.text = movieViewObject.title
            tvRating.text = "${movieViewObject.voteAverage}/10"
            tvVotes.text = "${movieViewObject.voteCount} votes"
            tvReleaseDate.text = movieViewObject.releaseDate
            tvLanguage.text = movieViewObject.originalLanguage
            tvOverview.text = movieViewObject.overview

            Glide.with(this@MovieDetailActivity)
                .load(movieViewObject.posterPath).into(ivPoster)

            Glide.with(this@MovieDetailActivity)
                .load(movieViewObject.backdropPath).into(ivBackground)
        }
    }

    companion object {
        private const val EXTRA_MOVIE_VIEW_OBJECT = "extra.movie_view_object"

        fun newIntent(context: Context, movieViewObject: MovieViewObject): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_VIEW_OBJECT, movieViewObject)
            return intent
        }
    }
}