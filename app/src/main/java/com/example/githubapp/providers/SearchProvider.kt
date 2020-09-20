package com.example.githubapp.providers

import android.content.Context
import android.widget.Toast
import com.example.githubapp.api.interfaces.Api
import com.example.githubapp.di.interfaces.DaggerSearchComponent
import com.example.githubapp.di.interfaces.SearchComponent
import com.example.githubapp.di.module.ContextModule
import com.example.githubapp.models.Result
import com.example.githubapp.presenters.SearchPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class SearchProvider(var presenter:SearchPresenter) {
    private val TAG: String = SearchProvider::class.java.simpleName

    lateinit var searchComponent: SearchComponent
    @Inject
    lateinit var api: Api

    fun loadUsers(login:String,page:Int,context:Context,boolean: Boolean){

        if(boolean) {
            searchComponent = DaggerSearchComponent.builder().contextModule(ContextModule(context=context)).build()

           val repository = searchComponent.retrofitApi()
               .getUsersList(login=login,page=page)
               .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    val userList: ArrayList<Result> = ArrayList()
                    userList.add(it)
                    userList[0].items?.let { it1 -> presenter.searchLoaded(it1) }
                }, { error -> error.printStackTrace() })
        }else{
            Toast.makeText(context,"No connected", Toast.LENGTH_LONG).show()
        }

    }


}