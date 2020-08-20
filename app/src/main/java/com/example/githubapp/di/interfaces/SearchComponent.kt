package com.example.githubapp.di.interfaces

import com.example.githubapp.api.interfaces.Api
import com.example.githubapp.api.module.RetrofitModule
import dagger.Component
import dagger.Module

@Component(modules = [RetrofitModule::class])
interface SearchComponent {
  fun retrofitApi(): Api
}