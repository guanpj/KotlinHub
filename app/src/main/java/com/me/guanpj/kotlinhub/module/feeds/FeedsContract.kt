package com.me.guanpj.kotlinhub.module.feeds

import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.IPresenter

interface FeedsContract {
    interface View : IMvpView {
    }

    interface Presenter : IPresenter<View> {

    }
}