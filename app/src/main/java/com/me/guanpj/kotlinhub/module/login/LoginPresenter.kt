package com.me.guanpj.kotlinhub.module.login

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.data.remote.model.AccountManager
import com.me.guanpj.kotlinhub.data.remote.service.AuthApi
import com.me.guanpj.kotlinhub.data.remote.service.AuthService
import com.me.guanpj.kotlinhub.data.remote.service.UserService
import com.me.guanpj.kotlinhub.entity.AuthorizationReq
import com.me.guanpj.kotlinhub.util.RxUtil
import io.reactivex.Observable
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    @Inject
    lateinit var authApi: AuthApi

    fun checkUserName(name: String): Boolean {
        return true
    }

    fun checkPasswd(passwd: String): Boolean {
        return true
    }

    fun doLogin(name: String, passwd: String) {
        AccountManager.username = name
        AccountManager.passwd = passwd
        /*AccountManager.login()
                .compose(RxUtil.observableThreadTransformer())
                .subscribe({
                    view.onLoginSuccess()
                }, {
                    view.onLoginError(it)
                })*/
        authApi.createAuthorization(AuthorizationReq())
                .compose(RxUtil.observableThreadTransformer())
                .doOnNext {
                    if (it.token.isEmpty()) throw AccountManager.AccountException(it)
                }
                .retryWhen {
                    it.flatMap {
                        if (it is AccountManager.AccountException) {
                            AuthService.deleteAuthorization(it.authorizationRsp.id)
                        } else {
                            Observable.error(it)
                        }
                    }
                }
                .flatMap {
                    AccountManager.token = it.token
                    AccountManager.authId = it.id
                    UserService.getAuthenticatedUser()
                }
                .map {
                    AccountManager.currentUser = it
                    AccountManager.notifyLogin(it)
                }
                .subscribe({
                    view.onLoginSuccess()
                }, {
                    view.onLoginError(it)
                })
    }

}