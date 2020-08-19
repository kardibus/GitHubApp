package com.example.githubapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.githubapp.models.Result
import com.example.githubapp.models.UsersApiResponse

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SearchView:MvpView {

    fun showError(textResource:Int)
    fun setupEmptyList()
    fun setupFriendsList(userList: ArrayList<UsersApiResponse>)
    fun startLoading()
    fun endLoading()

}