package com.example.spotiplus.model

import com.example.spotiplus.model.repository.RepositoryError
import com.example.spotiplus.model.repository.RepositoryResponse

interface ResponseListener<T> {
    fun onResponse(response: RepositoryResponse<T>)

    fun onError(repositoryError: RepositoryError)

}
