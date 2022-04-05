package com.jasindagebyriani.movieapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jasindagebyriani.movieapp.R
import com.jasindagebyriani.movieapp.view.FavoriteFragment
import com.jasindagebyriani.movieapp.view.PopularFragment
import com.jasindagebyriani.movieapp.view.TopRatedFragment

class MoviePagerAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    override fun getItemCount(): Int = tabData.size

    override fun createFragment(position: Int): Fragment {
        return tabData[position].second
    }

    companion object {
        val tabData = listOf(
            R.string.popular to PopularFragment(),
            R.string.top_rated to TopRatedFragment(),
            R.string.favorite to FavoriteFragment()
        )
    }
}