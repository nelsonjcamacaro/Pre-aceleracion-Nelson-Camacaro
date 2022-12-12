package com.example.spotiplus.data.repository

interface ResponseListener<T> {
    fun onResponse(response: RepositoryResponse<T>)

    fun onError(repositoryError: RepositoryError)

}
