package com.example.githubapp.providers.networkCheckIsOnline.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ContextModule{

    private var context:Context

    @Inject
    constructor(context: Context){
        this.context=context
    }

    @Provides
    fun context(): Context {return context.applicationContext}

}