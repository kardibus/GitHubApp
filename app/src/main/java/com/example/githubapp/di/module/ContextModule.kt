package com.example.githubapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    private lateinit var context: Context

    constructor(context: Context){
        this.context=context
    }

    @Provides
    fun context():Context{
        return context.applicationContext
    }
}