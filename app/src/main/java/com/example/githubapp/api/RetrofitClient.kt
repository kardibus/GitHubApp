package com.example.githubapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object Factory {
      fun create():Api {
          val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .baseUrl("https://api.github.com/").build()

          return retrofit.create(Api::class.java)
      }
    }
}
