package com.example.githubapp.di.interfaces

import com.example.githubapp.api.interfaces.Api
import com.example.githubapp.api.module.RetrofitModule
import com.example.githubapp.di.module.PicassoModule
import com.example.githubapp.di.networkCheckIsOnline.module.CheckEthernetModule
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Scope

@SearchScope
@Component(modules = [RetrofitModule::class, PicassoModule::class, CheckEthernetModule::class])
interface SearchComponent {
  fun retrofitApi(): Api
  fun picasso():Picasso
  fun check(): CheckEthernetModule
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchScope