package com.example.criticaltechworks_newsapp.presentation.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.criticaltechworks_newsapp.databinding.FragmentNewsHeadlineListBinding
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsHeadlinesListFragment : Fragment() {

    private lateinit var binding: FragmentNewsHeadlineListBinding
    private val viewModel: NewsHeadlinesViewModel by viewModels()

    private lateinit var headlineListAdapter: NewsHeadlinesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsHeadlineListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        observerData()
    }

    private fun setupViews() {
        headlineListAdapter =
            NewsHeadlinesListAdapter(context = requireContext(), onClick = {
                findNavController().navigate(
                    NewsHeadlinesListFragmentDirections.actionNewsHeadlinesListFragmentToNewsDetailFragment(
                        newsItem = it
                    )
                )
            })
        binding.rvHeadLines.adapter = headlineListAdapter

    }

    private fun setupListeners() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            headlineListAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefreshLayout.isRefreshing =
                    loadStates.refresh is LoadState.Loading
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            headlineListAdapter.refresh()
        }
    }

    private fun observerData() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main)  {
            viewModel.items.collectLatest {
                headlineListAdapter.submitData(it)
            }
        }
    }
}