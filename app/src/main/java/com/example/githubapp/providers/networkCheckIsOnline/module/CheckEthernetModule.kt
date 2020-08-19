package com.example.githubapp.providers.networkCheckIsOnline.module

import android.content.Context
import android.net.ConnectivityManager
import com.example.githubapp.providers.networkCheckIsOnline.module.ContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [ContextModule::class])
class CheckEthernetModule{

     var context:Context

    @Inject
    constructor(context: Context){
        this.context=context
    }

    @Provides
    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}