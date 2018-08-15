package com.me.guanpj.kotlinhub.module.main

import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.IPresenter

interface MainContract {
    interface View : IMvpView {
    }

    interface Presenter : IPresenter<View> {

    }
}