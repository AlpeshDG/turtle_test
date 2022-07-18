package com.alpeshg.turtletest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alpeshg.turtletest.data.db.dao.CommentDao
import com.alpeshg.turtletest.data.db.dao.IssueDao
import com.alpeshg.turtletest.data.db.dao.UserDao
import com.alpeshg.turtletest.data.model.Comment
import com.alpeshg.turtletest.data.model.Issue
import com.alpeshg.turtletest.data.model.User

@Database(entities = [Issue::class, User::class, Comment::class], version = 1, exportSchema = false)
abstract class IssueDB : RoomDatabase() {
    abstract fun issueDao(): IssueDao
    abstract fun commentDao(): CommentDao
    abstract fun userDao(): UserDao
}