package com.example.app.data.db.entities.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_entity")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "commentId") val commentId: Int,
    @ColumnInfo(name = "comment") val comment: String?,
)