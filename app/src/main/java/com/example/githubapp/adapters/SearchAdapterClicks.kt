package com.example.githubapp.adapters

import com.example.githubapp.models.UsersApiResponse

interface SearchAdapterClicks{
    fun onItemClick(model: UsersApiResponse)
}