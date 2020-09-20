package com.example.githubapp.di.module

import android.content.Context
import com.example.githubapp.di.interfaces.SearchScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named


@Module
class ContextModule {

    private var context: Context

    @Inject
    constructor(context: Context){
        this.context=context
    }

    @Provides
    fun context():Context{
        return context.applicationContext
    }
}