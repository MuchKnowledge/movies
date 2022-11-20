package com.example.app.di

import android.content.Context
import androidx.room.Room
import com.example.app.data.db.base.Database
import com.example.app.data.db.dao.CommentDao
import com.example.app.data.db.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): Database =
        Room.databaseBuilder(appContext, Database::class.java, "database")
            .build()

    @Singleton
    @Provides
    fun provideMoviesDao(database: Database): MoviesDao =
        database.moviesDao()

    @Singleton
    @Provides
    fun provideCommentDao(database: Database): CommentDao =
        database.commentDao()
}
