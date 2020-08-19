package com.example.githubapp.providers

import android.content.Context
import android.widget.Toast
import com.example.githubapp.api.SearchRepositoryProvider
import com.example.githubapp.models.Result
import com.example.githubapp.presenters.SearchPresenter
import com.example.githubapp.providers.networkCheckIsOnline.module.ContextModule
import com.example.githubapp.providers.networkCheckIsOnline.component.EthernetComponent
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchProvider(var presenter:SearchPresenter) {
    private val TAG: String = SearchProvider::class.java.simpleName

    lateinit var ethernetComponent: EthernetComponent

    fun loadUsers(login:String,page:Int,context:Context,boolean: Boolean){


        if(boolean) {
            val repository = SearchRepositoryProvider.provideAllApiResponse()
                .usersList(login = login, page = page).observeOn(AndroidSchedulers.mainThread())
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