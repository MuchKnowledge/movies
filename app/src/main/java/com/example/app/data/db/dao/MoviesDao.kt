package com.example.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.app.data.db.entities.movie.MovieEntity

@Dao
interface MoviesDao {

    @Transaction
    @Query("SELECT * FROM movies_entity WHERE movieId = :movieId")
    fun getMovie(movieId: String): MovieEntity

    @Transaction
    @Insert(onConflict = REPLACE)
    fun saveMovie(movie: MovieEntity)
}