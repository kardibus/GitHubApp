package com.example.githubapp.di.module

import android.content.Context
import com.example.githubapp.di.interfaces.SearchScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient


@Module(includes = [OkHttpClientModule::class,ContextModule::class])
class PicassoModule {

    @SearchScope
    @Provides
    fun picasso(context:Context, okHttp3Downloader: OkHttp3Downloader):Picasso{
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }

    @SearchScope
    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient):OkHttp3Downloader{
        return OkHttp3Downloader(okHttpClient)
    }
}