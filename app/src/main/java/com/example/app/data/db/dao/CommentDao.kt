package com.example.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.app.data.db.entities.comment.CommentEntity

@Dao
interface CommentDao {

    @Transaction
    @Insert
    fun saveComment(comment: CommentEntity)
}