package com.example.app.presentation.screens.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app.presentation.binding.viewBinding
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeMainStates()
    }

    private fun observeMainStates() {
        viewModel.state.observe(this) {
            when (it) {
                MainScreenState.Init -> showInitState()
                MainScreenState.Loading -> showLoadingState()
                MainScreenState.Empty -> showEmptyState()
                is MainScreenState.Loaded -> showLoadedState()
            }
        }
    }

    private fun showInitState() {
        lifecycleScope.launch {
            viewModel.search("sherlock", 2)
        }
    }

    private fun showLoadingState() {
    }

    private fun showEmptyState() {

    }

    private fun showLoadedState() {

    }
}