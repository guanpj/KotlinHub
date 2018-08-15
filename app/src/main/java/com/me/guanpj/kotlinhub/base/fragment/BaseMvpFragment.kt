package com.me.guanpj.kotlinhub.base.fragment

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import javax.inject.Inject

abstract class BaseMvpFragment<P : BasePresenter<BaseMvpFragment<P>>> : BaseFragment(), IMvpView<P> {
    @Inject
    override
    lateinit var presenter: P


}