package com.myapp.newyorktimes.ui.books

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.newyorktimes.databinding.FragmentBooksBinding
import com.myapp.newyorktimes.ui.BaseFragment
import com.myapp.newyorktimes.ui.books.adapter.BookAdapter
import com.myapp.newyorktimes.util.isInternetAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : BaseFragment<FragmentBooksBinding>() {
    private val viewModel: BooksViewModel by viewModel()
    private var booksAdapter = BookAdapter(arrayListOf())
    private val args: BooksFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupAdapter()
        setupRecycler()
        setupButtons()
        viewModel.checkInternet(args.lists, requireContext().isInternetAvailable())
    }

    private fun setupButtons() {
        binding.backButton.setOnClickListener { popBackStack() }
    }

    private fun setupObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            booksAdapter.addItems(it)
        }
    }

    private fun setupAdapter() {
        booksAdapter.onClickItem = { lists ->
            lists?.let {
                if (requireContext().isInternetAvailable()) navigate(BooksFragmentDirections.toBuy(it)) else internetErrorMessage()
            }
        }
    }

    private fun setupRecycler() = with(binding) {
        recycler.apply {
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun inflateBinding() = FragmentBooksBinding.inflate(layoutInflater)
}