package com.example.newsapp.presentation.newsList

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.utils.ListViewType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsListViewModel by viewModels()

    private lateinit var headlineListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater)
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
            NewsListAdapter(context = requireContext(), onClick = {
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsHeadlinesListFragmentToNewsDetailFragment(
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


        binding.btnGridView.setOnClickListener {
            viewModel.setListViewType(listViewType = ListViewType.GRID)
        }

        binding.btnListview.setOnClickListener {
            viewModel.setListViewType(listViewType = ListViewType.LINEAR)
        }

        binding.etSearch.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (event == null || !event.isShiftPressed) {
                    // the user is done typing.
                    viewModel.searchData(textView.text.toString())
                }
            }
            false
        }
    }

    private fun observerData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentQuery.collectLatest {
                headlineListAdapter.refresh()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.items.collectLatest {
                headlineListAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listViewType.collectLatest {
                when (it) {
                    ListViewType.LINEAR -> {
                        setSpanCount(1)
                        binding.btnListview.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.blue
                            )
                        )
                        binding.btnGridView.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gray_text
                            )
                        )
                    }

                    ListViewType.GRID -> {
                        setSpanCount(2)
                        binding.btnListview.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gray_text
                            )
                        )
                        binding.btnGridView.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.blue
                            )
                        )
                    }
                }
                headlineListAdapter.setListViewType(it)
            }
        }
    }

    private fun setSpanCount(columnCount: Int) {
        (binding.rvHeadLines.layoutManager as GridLayoutManager).spanCount = columnCount
    }
}