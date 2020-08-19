package com.example.githubapp.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.githubapp.models.UserApiResponse
import com.example.githubapp.providers.UserProvider
import com.example.githubapp.views.UserView

@InjectViewState
class UserPresenter: MvpPresenter<UserView>() {

   fun loadUser(login:String,context: Context,boolean: Boolean){
      UserProvider(presenter = this).getUser(login=login,context = context,boolean = boolean)
   }

   fun searchLoaded(userList: UserApiResponse){
         viewState.setupUser(userList = userList)
   }
}