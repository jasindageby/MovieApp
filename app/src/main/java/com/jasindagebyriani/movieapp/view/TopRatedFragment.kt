package com.jasindagebyriani.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jasindagebyriani.movieapp.databinding.FragmentTopRatedBinding
import com.jasindagebyriani.movieapp.presenter.TopRatedContract
import com.jasindagebyriani.movieapp.view.adapter.MovieAdapter
import com.jasindagebyriani.movieapp.view.viewobject.MovieViewObject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : Fragment(), TopRatedContract.View {

    @Inject
    lateinit var presenter: TopRatedContract.Presenter

    private lateinit var binding: FragmentTopRatedBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        presenter.loadData()
        showLoading(true)

        initRecyclerView()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showList(movies: List<MovieViewObject>) {
        showLoading(false)
        adapter.submitList(movies)
    }

    override fun showError() {
        showLoading(false)
        binding.errorContainer.root.isVisible = true
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieAdapter {
            presenter.clickFavorite(it)
        }
        binding.rvTopRated.layoutManager = layoutManager
        binding.rvTopRated.adapter = adapter
    }
}