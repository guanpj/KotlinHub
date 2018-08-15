package com.me.guanpj.kotlinhub.module.login

import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.IPresenter

interface LoginContract {
    interface View : IMvpView {
        fun onLoginError(e: Throwable)
        fun onLoginSuccess()
    }

    interface Presenter : IPresenter<View> {

    }
}