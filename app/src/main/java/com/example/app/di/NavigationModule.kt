package com.example.app.di

import android.content.Context
import com.example.app.presentation.navigation.MainRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideMainRouter(@ApplicationContext context: Context): MainRouter = MainRouter(context)
}