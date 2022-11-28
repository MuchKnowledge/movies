package com.example.app.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.app.data.models.MovieData
import com.example.app.presentation.bottom_sheets.search_params.SearchParamsBottomSheet
import com.example.app.presentation.screens.details.DetailsActivity
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.loadWithGlide
import com.example.app.utils.extension.roundCorners
import com.example.app.utils.extension.safeClick
import com.example.app.utils.quickInit
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.databinding.ItemMoviesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeMainStates()
        initListeners()
        supportActionBar?.hide()
    }

    private fun observeMainStates() {
        viewModel.state.observe(this) {
            when (it) {
                MainScreenState.Init -> showInitState()
                MainScreenState.Loading -> showLoadingState()
                is MainScreenState.Loaded -> showLoadedState(it.movies)
                is MainScreenState.Error -> showErrorState(it.message)
            }
        }
    }

    private fun initListeners() {
        with(binding.topBar) {
            imageSearch.setOnClickListener { navigateToSearchParamsBottomSheet() }
            imageBack.setOnClickListener { viewModel.setInitPosition() }
        }
    }

    private fun navigateToSearchParamsBottomSheet() {
        SearchParamsBottomSheet(onClick = { title, page -> viewModel.search(title, page) })
            .show(supportFragmentManager, getString(R.string.search_params_tag))
    }

    private fun showInitState() {
        with(binding) {
            textPlaceholder.text = resources.getString(R.string.main_screen_placeholder_init)
            textPlaceholder.isVisible = true
            topBar.imageBack.isVisible = false
            recyclerMovies.isVisible = false
            progress.isVisible = false
        }
    }

    private fun showLoadingState() {
        with(binding) {
            textPlaceholder.isVisible = false
            topBar.imageBack.isVisible = false
            progress.isVisible = true
        }
    }

    private fun showLoadedState(movies: List<MovieData>) {
        with(binding) {
            recyclerMovies.quickInit(
                items = movies,
                itemLayout = R.layout.item_movies,
                bind = { item, _ -> bindMovieItem(this, item) }
            )
            recyclerMovies.isVisible = true
            progress.isVisible = false
            topBar.imageBack.isVisible = true
        }
    }

    private fun showErrorState(text: String) {
        with(binding) {
            Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
            viewModel.setInitPosition()
        }
    }

    private fun bindMovieItem(view: View, item: MovieData) {
        ItemMoviesBinding.bind(view).apply {
            root.roundCorners(radius = 16f)
            root.setOnClickListener { navigateToDetailsActivity(item.id) }

            imagePoster.loadWithGlide(item.poster)
            imagePoster.roundCorners(radius = 16f)

            textTitle.text = item.title
            textYear.text = item.year
            textType.text = item.type
        }
    }

    private fun navigateToDetailsActivity(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(INTENT_EXTRA_ID, id)
        startActivity(intent)
    }

    companion object {
        const val INTENT_EXTRA_ID = "intent_extra_id"
    }
}