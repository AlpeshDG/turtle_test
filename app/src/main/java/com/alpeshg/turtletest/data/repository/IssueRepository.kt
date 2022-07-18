package com.alpeshg.turtletest.data.repository

import com.alpeshg.turtletest.data.api.IssueService
import com.alpeshg.turtletest.data.db.IssueDB
import com.alpeshg.turtletest.data.model.Comment
import com.alpeshg.turtletest.data.model.Issue

class IssueRepository(private val issueService: IssueService, private val issueDB: IssueDB) {

    suspend fun getIssues() = issueService.getIssues()

    suspend fun getComments(issueNumber: Int) = issueService.getComments(issueNumber)

    fun getIssuesFromDB(): List<Issue> {
        val issueList = issueDB.issueDao().getAllIssues()
        issueList.forEach {
            it.user = issueDB.userDao().getUser(it.userId)
        }
        return issueList
    }

    fun saveIssuesInDB(issueList: List<Issue>) {
        issueList.forEach {
            it.userId = it.user.id
            issueDB.userDao().insert(it.user)
        }
        issueDB.issueDao().insertAll(issueList)
    }

    fun getCommentsFromDB(issueNumber: Int): List<Comment> {
        val commentList = issueDB.commentDao().getCommentsForIssue(issueNumber)
        commentList.forEach {
            it.user = issueDB.userDao().getUser(it.userId)
        }
        return commentList
    }

    fun saveCommentsInDB(issueNumber: Int, commentList: List<Comment>) {
        commentList.forEach {
            it.number = issueNumber
            it.userId = it.user.id
            issueDB.userDao().insert(it.user)
        }
        issueDB.commentDao().insertAll(commentList)
    }
}