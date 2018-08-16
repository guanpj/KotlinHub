package com.me.guanpj.kotlinhub.data.remote.api

import com.me.guanpj.kotlinhub.entity.User
import com.me.guanpj.kotlinhub.util.GitHubPaging
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>

    @GET("/users")
    fun allUsers(@Query("since") since: Int, @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<User>>

    @GET("/users/{login}/following")
    fun following(@Path("login") login: String, @Query("page") page: Int = 1, @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<User>>

    @GET("/users/{login}/followers")
    fun followers(@Path("login") login: String, @Query("page") page: Int = 1): Observable<GitHubPaging<User>>

}