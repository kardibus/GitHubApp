package com.example.githubapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.githubapp.models.UserApiResponse

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface UserView:MvpView {


    fun setupUser(userList: UserApiResponse)

}