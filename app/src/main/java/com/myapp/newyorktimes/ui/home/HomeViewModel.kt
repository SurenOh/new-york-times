package com.myapp.newyorktimes.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.newyorktimes.datastorage.LocalDataStorage
import com.myapp.newyorktimes.datastorage.NetworkDataStorage
import com.myapp.newyorktimes.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val networkDataStorage: NetworkDataStorage,
    private val localDataStorage: LocalDataStorage
) : ViewModel() {
    val categories = MutableLiveData<ArrayList<CategoryModel>>()

    fun checkInternet(isInternetAvailable: Boolean) {
        if (isInternetAvailable) getNetWorkCategories() else getCacheCategories()
    }

    private fun getNetWorkCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryValues = networkDataStorage.getCategories()
            categoryValues?.let {
                val sortedValues = ArrayList(it.sortedBy { element -> element.name })
                categories.postValue(sortedValues)
                saveCacheCategories(sortedValues)
            }
        }
    }

    private fun getCacheCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val cacheCategories = localDataStorage.getCategories().sortedBy { element -> element.name }
            categories.postValue(ArrayList(cacheCategories))
        }
    }

    private fun saveCacheCategories(list: List<CategoryModel>) {
        localDataStorage.insertCategories(list)
    }
}