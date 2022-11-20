package com.example.app.data.db.entities.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.app.data.db.entities.comment.CommentEntity
import com.example.app.data.db.entities.crossRef.MovieCommentCrossRef

data class MovieWithComment(
    @Embedded val movie: MovieEntity,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "commentId",
        associateBy = Junction(MovieCommentCrossRef::class)
    )
    val songs: List<CommentEntity>
)