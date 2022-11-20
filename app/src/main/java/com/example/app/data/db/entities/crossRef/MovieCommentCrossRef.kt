package com.example.app.data.db.entities.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_comment_cross_ref", primaryKeys = ["movieId", "commentId"])
data class MovieCommentCrossRef(
    @ColumnInfo(name = "commentId") val commentId: Int,
    @ColumnInfo(name = "movieId") val movieId: String,
)