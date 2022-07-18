package com.alpeshg.turtletest.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpeshg.turtletest.data.model.Comment
import com.alpeshg.turtletest.databinding.ListItemCommentBinding
import com.alpeshg.turtletest.utils.DateConverter
import com.bumptech.glide.Glide

class CommentListAdapter(private val commentList: List<Comment>) :
    RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = commentList[position]

        with(holder.binding) {
            tvDesc.text = issue.body
            tvUserName.text = "By ${issue.user.login}"
            Glide.with(root.context).load(issue.user.avatarUrl).into(ivUserAvatar)
            tvUpdatedAt.text = "on ${DateConverter.toDDMMYYYY(issue.updatedAt)}"
        }
    }

    override fun getItemCount() = commentList.size

    class ViewHolder(val binding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root)
}