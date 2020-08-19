package com.example.githubapp.api

object SearchRepositoryProvider {
    fun provideAllApiResponse(): SearchRepository {
        return SearchRepository(RetrofitClient.create())
    }
}