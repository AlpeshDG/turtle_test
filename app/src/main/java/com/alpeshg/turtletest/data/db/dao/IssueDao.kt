package com.alpeshg.turtletest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alpeshg.turtletest.data.model.Issue

@Dao
interface IssueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(issueList: List<Issue>)

    @Query("SELECT * FROM issue")
    fun getAllIssues(): List<Issue>
}