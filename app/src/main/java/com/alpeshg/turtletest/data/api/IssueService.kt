package com.alpeshg.turtletest.data.api

import com.alpeshg.turtletest.data.model.Comment
import com.alpeshg.turtletest.data.model.Issue
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IssueService {

    companion object {
        const val baseUrl = "https://api.github.com/repos/square/okhttp/"
    }

    @GET("issues")
    suspend fun getIssues(): Response<List<Issue>>

    @GET("issues/{id}/comments")
    suspend fun getComments(@Path("id") issueNumber: Int): Response<List<Comment>>

}