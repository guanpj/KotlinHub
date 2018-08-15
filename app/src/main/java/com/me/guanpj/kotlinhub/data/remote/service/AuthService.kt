package com.me.guanpj.kotlinhub.data.remote.service

import com.me.guanpj.kotlinhub.core.Configs
import com.me.guanpj.kotlinhub.data.remote.retrofit
import com.me.guanpj.kotlinhub.entity.AuthorizationReq
import com.me.guanpj.kotlinhub.entity.AuthorizationRsp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi{

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req: AuthorizationReq, @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint)
        : Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>

}

object AuthService: AuthApi by retrofit.create(AuthApi::class.java)