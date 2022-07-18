package com.alpeshg.turtletest.di

import androidx.room.Room
import com.alpeshg.turtletest.data.api.IssueService
import com.alpeshg.turtletest.data.db.IssueDB
import com.alpeshg.turtletest.data.repository.IssueRepository
import com.alpeshg.turtletest.ui.comment.CommentsViewModel
import com.alpeshg.turtletest.ui.issue.IssuesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val issueModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(IssueService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IssueService::class.java)
    }
    single {
        Room.databaseBuilder(androidContext(), IssueDB::class.java, "issue-db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        IssueRepository(get(), get())
    }
    viewModel {
        IssuesViewModel(get())
    }
    viewModel {
        CommentsViewModel(get())
    }
}