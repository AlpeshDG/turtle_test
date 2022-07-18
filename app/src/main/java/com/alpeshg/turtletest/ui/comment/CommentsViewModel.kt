package com.alpeshg.turtletest.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpeshg.turtletest.data.model.Comment
import com.alpeshg.turtletest.data.repository.IssueRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentsViewModel(private val issueRepository: IssueRepository) : ViewModel() {

    private val commentsLiveData = MutableLiveData<List<Comment>>()

    fun getComments(issueNumber: Int): LiveData<List<Comment>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = issueRepository.getComments(issueNumber)
            var commentList = mutableListOf<Comment>()
            if (response.isSuccessful) {
                response.body()?.let {
                    commentList = it.toMutableList()
                    issueRepository.saveCommentsInDB(issueNumber, it)
                }
            }
            commentsLiveData.postValue(commentList)
        }
        return commentsLiveData
    }

    fun getCommentsOffline(issueNumber: Int): LiveData<List<Comment>> {
        CoroutineScope(Dispatchers.IO).launch {
            val commentList = issueRepository.getCommentsFromDB(issueNumber)
            commentsLiveData.postValue(commentList)
        }
        return commentsLiveData
    }

}