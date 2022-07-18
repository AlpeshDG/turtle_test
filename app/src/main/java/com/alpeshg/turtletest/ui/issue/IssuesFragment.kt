package com.alpeshg.turtletest.ui.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpeshg.turtletest.R
import com.alpeshg.turtletest.databinding.FragmentIssueListBinding
import com.alpeshg.turtletest.ui.MainActivity
import com.alpeshg.turtletest.utils.isOnline
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment() {

    private lateinit var binding: FragmentIssueListBinding

    private val viewModel by viewModel<IssuesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIssueListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.issues)

        //init views
        initViews()

        //get issue list
        getIssueList()
    }

    private fun initViews() {
        binding.rvIssueList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun getIssueList() {
        binding.pbLoader.visibility = View.VISIBLE
        val issueListLiveData =
            if (isOnline(requireContext())) viewModel.getIssues() else viewModel.getIssuesOffline()
        issueListLiveData.observe(requireActivity(), Observer {
            binding.pbLoader.visibility = View.GONE
            it?.let {
                binding.rvIssueList.adapter = IssueListAdapter(it)
            }
        })
    }

}