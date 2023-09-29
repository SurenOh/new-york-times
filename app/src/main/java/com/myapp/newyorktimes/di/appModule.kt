package com.myapp.newyorktimes.di

import androidx.room.Room
import com.myapp.newyorktimes.datastorage.LocalDataStorage
import com.myapp.newyorktimes.datastorage.NetworkDataStorage
import com.myapp.newyorktimes.db.MyDatabase
import com.myapp.newyorktimes.mapper.book.BookDtoMapper
import com.myapp.newyorktimes.mapper.book.BookEntityMapper
import com.myapp.newyorktimes.mapper.category.CategoryDtoMapper
import com.myapp.newyorktimes.mapper.category.CategoryEntityMapper
import com.myapp.newyorktimes.network.service.BooksService
import com.myapp.newyorktimes.network.service.CategoriesService
import com.myapp.newyorktimes.repository.LocalRepository
import com.myapp.newyorktimes.repository.NetworkRepository
import com.myapp.newyorktimes.ui.books.BooksViewModel
import com.myapp.newyorktimes.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"
const val API_KEY = "Gu3H0svdAJxgMpQ6HlkJGfvIHNIams4g"
const val DB_NAME = "nyt_db"

val applicationModule = module {
    single {
        val client = OkHttpClient.Builder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            MyDatabase::class.java,
            DB_NAME
        ).build()
    }

    fun provideCategoryService(retrofit: Retrofit) = retrofit.create(CategoriesService::class.java)
    fun provideBooksService(retrofit: Retrofit) = retrofit.create(BooksService::class.java)

    factory { provideCategoryService(get()) }
    factory { provideBooksService(get()) }

    //Mappers
    single { CategoryDtoMapper() }
    single { BookDtoMapper() }
    single { BookEntityMapper() }
    single { CategoryEntityMapper() }

    //Repositories
    single { NetworkRepository(get(), get()) }
    single { LocalRepository(get()) }

    //DataStorages
    single { NetworkDataStorage(get(), get(), get()) }
    single { LocalDataStorage(get(), get(), get()) }

    //ViewModels
    viewModel { HomeViewModel(get(), get()) }
    viewModel { BooksViewModel(get(), get()) }

}