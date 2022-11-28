package com.example.app.data.db.entities.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_entity")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "movieId") val movieId: String,
    @ColumnInfo(name = "is_liked") val isLiked: Boolean?,
)