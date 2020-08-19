package com.example.githubapp.api

import com.example.githubapp.models.Result
import com.example.githubapp.models.UserApiResponse
import rx.Observable

class SearchRepository(val apiServise: Api) {
    fun usersList(login:String, page:Int): Observable<Result> {
        return apiServise.getUsersList(login = login,page = page)
    }

    fun user(login: String): Observable<UserApiResponse> {
        return apiServise.getUser(login=login)
    }
}