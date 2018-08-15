package com.me.guanpj.kotlinhub.base.activity

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<BaseMvpActivity<P>>>: BaseActivity(), IMvpView<P> {

    @Inject
    override
    lateinit var presenter: P

}