package com.alpeshg.turtletest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alpeshg.turtletest.data.model.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(commentList: List<Comment>)

    @Query("SELECT * FROM comment WHERE number = :issueNumber")
    fun getCommentsForIssue(issueNumber: Int): List<Comment>
}