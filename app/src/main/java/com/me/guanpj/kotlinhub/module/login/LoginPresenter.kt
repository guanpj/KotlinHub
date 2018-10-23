package com.me.guanpj.kotlinhub.module.login

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.core.AccountManager
import com.me.guanpj.kotlinhub.data.remote.api.AuthApi
import com.me.guanpj.kotlinhub.data.remote.api.UserApi
import com.me.guanpj.kotlinhub.entity.AuthorizationReq
import com.me.guanpj.kotlinhub.entity.AuthorizationRsp
import io.reactivex.Observable
import retrofit2.HttpException
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    @Inject
    lateinit var authApi: AuthApi
    @Inject
    lateinit var userApi: UserApi

    fun checkUserName(name: String): Boolean {
        return true
    }

    fun checkPasswd(passwd: String): Boolean {
        return true
    }

    fun doLogin(name: String, psw: String) {
        AccountManager.userName = name
        AccountManager.password = psw
        authApi.createAuthorization(AuthorizationReq())
                .doOnNext {
                    if (it.token.isEmpty()) throw AccountException(it)
                }
                .retryWhen {
                    it.flatMap {
                        if (it is AccountException) {
                            authApi.deleteAuthorization(it.authorizationRsp.id)
                        } else {
                            Observable.error(it)
                        }
                    }
                }
                .flatMap {
                    AccountManager.token = it.token
                    AccountManager.authId = it.id
                    userApi.getAuthenticatedUser()
                }
                .map {
                    AccountManager.currentUser = it
                    //AccountManager.notifyLogin(it)
                }
                .subscribe({
                    getView().onLoginSuccess()
                }, {
                    getView().onLoginError(it)
                })
                .also { addDisposable(it) }
    }

    fun logout() = authApi.deleteAuthorization(AccountManager.authId)
            .doOnNext {
                if (it.isSuccessful) {
                    AccountManager.authId = -1
                    AccountManager.token = ""
                    AccountManager.currentUser = null
                    //AccountManager.notifyLogout(it)
                } else {
                    throw HttpException(it)
                }
            }

    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}