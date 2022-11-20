package com.example.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.app.data.db.entities.movie.MovieEntity
import com.example.app.data.db.entities.movie.MovieWithComment

@Dao
interface MoviesDao {

    @Transaction
    @Query("SELECT * FROM movie_comment_cross_ref WHERE movieId = :movieId")
    fun getFullMovie(movieId: String): MovieWithComment

    @Transaction
    @Insert(onConflict = REPLACE)
    fun saveMovie(movie: MovieEntity)
}