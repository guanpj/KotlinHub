package com.me.guanpj.kotlinhub.base

interface IPresenter<out V : IMvpView> {
    val view: V

    fun onAttatch(view: @UnsafeVariance V)

    fun onDetach()
}

interface IMvpView {
}