package com.alpeshg.turtletest.ui.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpeshg.turtletest.data.db.IssueDB
import com.alpeshg.turtletest.data.model.Issue
import com.alpeshg.turtletest.data.repository.IssueRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IssuesViewModel(private val issueRepository: IssueRepository) : ViewModel() {

    private val issuesLiveData = MutableLiveData<List<Issue>>()

    fun getIssues(): LiveData<List<Issue>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = issueRepository.getIssues()
            var issueList = mutableListOf<Issue>()
            if (response.isSuccessful) {
                response.body()?.let {
                    issueList = it.toMutableList()
                    issueRepository.saveIssuesInDB(it)
                }
            }
            issuesLiveData.postValue(issueList)
        }
        return issuesLiveData
    }

    fun getIssuesOffline(): LiveData<List<Issue>> {
        CoroutineScope(Dispatchers.IO).launch {
            val issueList = issueRepository.getIssuesFromDB()
            issuesLiveData.postValue(issueList)
        }
        return issuesLiveData
    }

}