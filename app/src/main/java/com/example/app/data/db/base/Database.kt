package com.example.app.data.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.data.db.dao.CommentDao
import com.example.app.data.db.dao.MoviesDao
import com.example.app.data.db.entities.comment.CommentEntity
import com.example.app.data.db.entities.crossRef.MovieCommentCrossRef
import com.example.app.data.db.entities.movie.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        CommentEntity::class,
        MovieCommentCrossRef::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun commentDao(): CommentDao
}