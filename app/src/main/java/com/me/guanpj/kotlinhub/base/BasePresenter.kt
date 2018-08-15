package com.me.guanpj.kotlinhub.base

abstract class BasePresenter<out V: IMvpView<BasePresenter<V>>>: IPresenter<V> {
    override lateinit var view: @UnsafeVariance V

    override fun onAttatch(view: @UnsafeVariance V) {
        this.view = view
    }

    override fun onDetach() {
    }
}