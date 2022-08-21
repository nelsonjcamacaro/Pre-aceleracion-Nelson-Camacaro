package com.example.spotiplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spotiplus.model.MoviesRemoteDataSource
import com.example.spotiplus.model.repository.MoviesRepository

class MoviesViewModelFactory:ViewModelProvider.Factory {
    override fun <T:ViewModel> create(modelClass:Class<T>):T{
        val remoteDataSource = MoviesRemoteDataSource()
        val repository = MoviesRepository(remoteDataSource)

        return MoviesViewModel(repository) as T
    }
}