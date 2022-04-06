package com.jasindagebyriani.movieapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jasindagebyriani.movieapp.databinding.ActivityMainBinding
import com.jasindagebyriani.movieapp.presenter.MainContract
import com.jasindagebyriani.movieapp.view.adapter.MoviePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabPagerAdapter()
        presenter.attachView(this)
        presenter.loadTotalFavorite()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun initTabPagerAdapter() {
        binding.viewpager.adapter = MoviePagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = getString(MoviePagerAdapter.tabData[position].first)
        }.attach()
    }

    override fun showTotalFavorite(total: Int) {
        val favoriteIndex = MoviePagerAdapter.tabData
            .indexOfFirst { (_, fragment) -> fragment is FavoriteFragment }
        val defaultTitle = getString(MoviePagerAdapter.tabData[favoriteIndex].first)
        val result = if (total > 0) "$defaultTitle(${total})" else defaultTitle
        binding.tabLayout.getTabAt(favoriteIndex)?.text = result
    }
}