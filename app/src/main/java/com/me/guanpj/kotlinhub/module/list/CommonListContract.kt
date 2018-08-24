package com.me.guanpj.kotlinhub.module.list

import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.IPresenter

interface CommonListContract {
    interface View : IMvpView {
    }

    interface Presenter : IPresenter<View> {

    }
}