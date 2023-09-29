package com.myapp.newyorktimes.ui.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.newyorktimes.datastorage.LocalDataStorage
import com.myapp.newyorktimes.datastorage.NetworkDataStorage
import com.myapp.newyorktimes.model.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksViewModel(
    private val networkDataStorage: NetworkDataStorage,
    private val localDataStorage: LocalDataStorage
) : ViewModel() {
    val books = MutableLiveData<ArrayList<BookModel>>()

    fun checkInternet(lists: String, isInternetAvailable: Boolean) {
        if (isInternetAvailable) getNetworkBooks(lists) else getCacheBooks(lists)
    }

    private fun getNetworkBooks(lists: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bookValue = networkDataStorage.getBooks(lists)
            bookValue?.let {
                val sortedItems = ArrayList(it.sortedBy { element -> element.rank })
                books.postValue(sortedItems)
                saveCacheBooks(sortedItems)
            }
        }
    }

    private fun getCacheBooks(lists: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bookValues = localDataStorage.getBooks(lists).sortedBy { element -> element.rank }
            books.postValue(ArrayList(bookValues))
        }
    }

    private fun saveCacheBooks(list: List<BookModel>) {
        localDataStorage.insertBooks(list)
    }
}