package com.example.app.di

import com.example.app.data.api.MoviesApi
import com.example.app.data.repositories.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepositoryImpl(
        moviesApi: MoviesApi,
    ): MoviesRepositoryImpl = MoviesRepositoryImpl(moviesApi)
}