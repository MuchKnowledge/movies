package com.example.app.presentation.screens.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.app.data.models.MovieData
import com.example.app.presentation.navigation.MainRouter
import com.example.app.presentation.screens.main.MainActivity
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.drawable
import com.example.app.utils.extension.roundCorners
import com.example.movies.R
import com.example.movies.databinding.ActivityDetalisBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetalisBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModels()

    @Inject lateinit var mainRouter: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getStringExtra(MainActivity.INTENT_EXTRA_ID)!!
        observeState()
        initListeners(movieId)
        viewModel.loadMovieById(movieId)
        supportActionBar?.hide()
    }

    private fun observeState() {
        viewModel.state.observe(this) {
            when (it) {
                DetailsScreenState.Loading -> showLoadingState()
                is DetailsScreenState.Loaded -> showLoadedState(it.movie)
            }
        }
    }

    private fun initListeners(movieId: String) {
        with(binding) {
            topBar.imageBack.setOnClickListener { finish() }
            buttonImdb.setOnClickListener { mainRouter.navigateToIMDbWebSite(movieId) }
        }
    }

    private fun showLoadingState() {
        with(binding) {
            includePoster.root.isVisible = false
            progress.isVisible = true
        }
    }

    private fun showLoadedState(item: MovieData) {
        with(binding) {
            progress.isVisible = false
            with(includePoster) {
                root.isVisible = true
                root.roundCorners(radius = 16f)
                imagePoster.roundCorners(radius = 16f)
                buttonImdb.roundCorners(radius = 16f)

                Glide.with(imagePoster)
                    .load(item.poster)
                    .into(imagePoster)

                textTitle.text = item.title
                textYear.text = item.year
                textType.text = item.type
                textDescription.text = item.plot
                textLanguage.text = item.language
                textWriter.text = item.writer
                textGenre.text = item.genre
                textImdbRating.text = item.imdbRating

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
    }
}