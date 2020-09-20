package com.example.githubapp.di.module

import com.example.githubapp.di.interfaces.SearchScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient


@Module(includes = [ContextModule::class])
class OkHttpClientModule {
    @SearchScope
    @Provides
    fun okHttpClient():OkHttpClient{
        return OkHttpClient().newBuilder().build()
    }
}