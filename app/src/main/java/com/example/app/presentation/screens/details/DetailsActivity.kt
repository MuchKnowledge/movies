package com.example.app.presentation.screens.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.example.app.data.api.NETWORK_IMDB_WEB_URL
import com.example.app.data.models.MovieData
import com.example.app.presentation.screens.main.MainActivity
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.loadWithGlide
import com.example.app.utils.extension.roundCorners
import com.example.movies.databinding.ActivityDetalisBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetalisBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModels()

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
            buttonImdb.setOnClickListener { navigateToIMDbWeb(movieId) }
        }
    }

    private fun navigateToIMDbWeb(id: String) {
        val uri = "$NETWORK_IMDB_WEB_URL/$id/"
        val intent = Intent(Intent.ACTION_VIEW, uri.toUri())
        val chooser = Intent.createChooser(intent, "Open with").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(chooser)
    }

    private fun showLoadingState() {
        with(binding) {
            includePoster.root.isVisible = false
            buttonImdb.isVisible = false
            progress.isVisible = true
        }
    }

    private fun showLoadedState(item: MovieData) {
        with(binding) {
            progress.isVisible = false
            buttonImdb.isVisible = true
            with(includePoster) {
                root.isVisible = true
                root.roundCorners(radius = 16f)
                buttonImdb.roundCorners(radius = 16f)
                imagePoster.roundCorners(radius = 16f)
                imagePoster.loadWithGlide(item.poster)

                textTitle.text = item.title
                textYear.text = item.year
                textType.text = item.type
                textDescription.text = item.plot
                textLanguage.text = item.language
                textWriter.text = item.writer
                textGenre.text = item.genre
                textImdbRating.text = item.imdbRating
            }
        }
    }
}