package com.myapp.newyorktimes.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.newyorktimes.databinding.FragmentHomeBinding
import com.myapp.newyorktimes.ui.BaseFragment
import com.myapp.newyorktimes.ui.home.adapter.CategoryAdapter
import com.myapp.newyorktimes.util.isInternetAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModel()
    private var categoryAdapter = CategoryAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupAdapter()
        setupRecycler()
        viewModel.checkInternet(requireContext().isInternetAvailable())
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.addItems(it)
        }
    }

    private fun setupAdapter() {
        categoryAdapter.onClickItem = { lists ->
            lists?.let { navigate(HomeFragmentDirections.toBooks(it)) }
        }
    }

    private fun setupRecycler() = with(binding) {
        recycler.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun inflateBinding() = FragmentHomeBinding.inflate(layoutInflater)
}