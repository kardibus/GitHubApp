package com.example.githubapp.providers


import android.content.Context
import android.widget.Toast
import com.example.githubapp.di.interfaces.DaggerSearchComponent
import com.example.githubapp.di.interfaces.SearchComponent
import com.example.githubapp.models.UserApiResponse
import com.example.githubapp.presenters.UserPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserProvider(var presenter: UserPresenter) {

    lateinit var searchComponent: SearchComponent

    fun getUser(login: String,context: Context,boolean: Boolean){

        if (boolean) {
            searchComponent = DaggerSearchComponent.builder().build()

            val repository = searchComponent.retrofitApi()
                .getUser(login=login).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({
                it
                val user: UserApiResponse = it
                user.let { it1 -> presenter.searchLoaded(it) }
            }, { error -> error.printStackTrace() })
        }else{
            Toast.makeText(context,"No connected",Toast.LENGTH_LONG).show()
        }
    }
}
