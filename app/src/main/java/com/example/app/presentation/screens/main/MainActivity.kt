package com.example.app.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.app.data.models.MovieData
import com.example.app.presentation.navigation.MainRouter
import com.example.app.presentation.screens.details.DetailsActivity
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.drawable
import com.example.app.utils.extension.roundCorners
import com.example.app.utils.quickInit
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.databinding.ItemMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var mainRouter: MainRouter

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
                MainScreenState.Empty -> showEmptyState()
                is MainScreenState.Loaded -> showLoadedState(it.movies)
            }
        }
    }

    private fun initListeners() {
        with(binding.topBar) {
            imageSearch.setOnClickListener { mainRouter.navigateToSearchParamsBottomSheet().show(supportFragmentManager, "") }
            imageBack.setOnClickListener { viewModel.onBackButtonClicked() }
        }
    }

    private fun showInitState() {
        with(binding) {
            textPlaceholder.text = resources.getString(R.string.main_screen_placeholder_init)
            viewModel.search("Sherlock", 2)
        }
    }

    private fun showLoadingState() {
        with(binding) {
            textPlaceholder.isVisible = false
            topBar.imageBack.isVisible = false
            progress.isVisible = true
        }
    }

    private fun showEmptyState() {
        with(binding) {
            textPlaceholder.text = resources.getString(R.string.main_screen_placeholder_empty)
            textPlaceholder.isVisible = true
            topBar.imageBack.isVisible = false
            progress.isVisible = false
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

    private fun bindMovieItem(view: View, item: MovieData) {
        ItemMoviesBinding.bind(view).apply {
            root.roundCorners(radius = 16f)
            root.setOnClickListener { navigateToDetailsActivity(item.id) }

            imagePoster.roundCorners(radius = 16f)

            Glide.with(imagePoster)
                .load(item.poster)
                .into(imagePoster)
            textTitle.text = item.title
            textYear.text = item.year
            textType.text = item.type

            imageLike.setImageDrawable(
                when (item.isLiked) {
                    true -> drawable(R.drawable.ic_liked)
                    false -> drawable(R.drawable.ic_unliked)
                }
            )
            imageLike.setOnClickListener {
                // should be changed
                val imageLikeDrawable =
                    if (imageLike.drawable == drawable(R.drawable.ic_liked)) drawable(R.drawable.ic_unliked)
                    else drawable(R.drawable.ic_liked)
                imageLike.setImageDrawable(imageLikeDrawable)
                viewModel.updateLikeStatus(item.id, item.isLiked.not())
            }
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