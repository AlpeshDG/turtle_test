package com.alpeshg.turtletest.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpeshg.turtletest.R
import com.alpeshg.turtletest.data.model.Issue
import com.alpeshg.turtletest.databinding.FragmentCommentListBinding
import com.alpeshg.turtletest.ui.MainActivity
import com.alpeshg.turtletest.ui.issue.IssueListAdapter
import com.alpeshg.turtletest.ui.issue.IssuesViewModel
import com.alpeshg.turtletest.utils.DateConverter
import com.alpeshg.turtletest.utils.isOnline
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsFragment : Fragment() {

    private lateinit var binding: FragmentCommentListBinding

    private val viewModel by viewModel<CommentsViewModel>()

    private lateinit var issue: Issue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.issue_info)

        //get issue data from bundle
        arguments?.let {
            issue = CommentsFragmentArgs.fromBundle(it).issue
        }

        //init views
        initViews()

        //set data
        setData()

        //get comment list
        getCommentList()
    }

    private fun initViews() {
        binding.rvCommentList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setData() {
        with(binding.issueInfo) {
            tvTitle.text = issue.title
            tvDesc.text = issue.body
            tvUserName.text = "By ${issue.user.login}"
            Glide.with(this@CommentsFragment).load(issue.user.avatarUrl).into(ivUserAvatar)
            tvUpdatedAt.text =
                "on ${com.alpeshg.turtletest.utils.DateConverter.toDDMMYYYY(issue.updatedAt)}"
        }
    }

    private fun getCommentList() {
        binding.pbLoader.visibility = View.VISIBLE
        val commentListLiveData =
            if (isOnline(requireContext())) viewModel.getComments(issue.number)
            else viewModel.getCommentsOffline(issue.number)
        commentListLiveData.observe(requireActivity(), Observer {
            binding.pbLoader.visibility = View.GONE
            it?.let {
                binding.rvCommentList.adapter = CommentListAdapter(it)
            }
        })
    }

}