package com.alpeshg.turtletest.ui.issue

import android.text.InputFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alpeshg.turtletest.data.model.Issue
import com.alpeshg.turtletest.databinding.ListItemIssueBinding
import com.alpeshg.turtletest.utils.DateConverter
import com.bumptech.glide.Glide

class IssueListAdapter(private val issueList: List<Issue>) :
    RecyclerView.Adapter<IssueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = issueList[position]

        with(holder.binding) {
            tvTitle.text = issue.title
            tvDesc.run {
                filters = arrayOf(InputFilter.LengthFilter(200))
                text = issue.body
            }
            tvUserName.text = "By ${issue.user.login}"
            Glide.with(root.context).load(issue.user.avatarUrl).into(ivUserAvatar)
            tvUpdatedAt.text = "on ${DateConverter.toDDMMYYYY(issue.updatedAt)}"
        }

        holder.itemView.setOnClickListener {
            val action = IssuesFragmentDirections.actionShowComments(issue)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = issueList.size

    class ViewHolder(val binding: ListItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root)
}