package com.example.githubapp.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.githubapp.R
import com.example.githubapp.models.UsersApiResponse
import com.example.githubapp.providers.SearchProvider
import com.example.githubapp.views.SearchView

@InjectViewState
class SearchPresenter:MvpPresenter<SearchView>() {

       fun loadSearch(login:String,page:Int,context: Context,boolean: Boolean){
           viewState.startLoading()
           SearchProvider(presenter = this).loadUsers(login = login,page = page,context = context,boolean = boolean)
       }

      fun searchLoaded(usersList: ArrayList<UsersApiResponse>){
          viewState.endLoading()
          if (usersList?.size==0){
              viewState.setupEmptyList()
              viewState.showError(textResource = R.string.user_no_items)
          }else {
              viewState.setupFriendsList(userList = usersList)
          }
      }
    fun showError(textResource:Int){
        viewState.showError(textResource=textResource)
    }

}