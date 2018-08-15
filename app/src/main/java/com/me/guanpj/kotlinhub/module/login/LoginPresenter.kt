package com.me.guanpj.kotlinhub.module.login

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.data.remote.model.AccountManager
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    fun checkUserName(name: String): Boolean {
        return true
    }

    fun checkPasswd(passwd: String): Boolean {
        return true
    }

    fun doLogin(name: String, passwd: String){
        AccountManager.login()
                .subscribe({
                    view.onLoginSuccess()
                }, {
                    view.onLoginError(it)
                })
    }

}