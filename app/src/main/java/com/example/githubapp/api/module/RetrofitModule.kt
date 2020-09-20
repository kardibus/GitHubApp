package com.example.githubapp.api.module

import com.example.githubapp.api.interfaces.Api
import com.example.githubapp.di.interfaces.SearchScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class RetrofitModule {

    @Provides
    fun usersApi(retrofit: Retrofit): Api {
      return retrofit.create(Api::class.java)
    }

    @SearchScope
    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl("https://api.github.com/").addConverterFactory(gsonConverterFactory).build()
    }

    @Provides
    fun gsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }


}