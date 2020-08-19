package com.example.githubapp.api


import com.example.githubapp.models.Result
import com.example.githubapp.models.UserApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface Api {

    @GET(value="users/{user}")
    fun getUser(@Path(value="user") login: String):Observable<UserApiResponse>

    @GET(value = "search/users")
    fun getUsersList(@Query(value="q") login:String, @Query(value = "page") page: Int):Observable<Result>
}