package com.me.guanpj.kotlinhub.base

import io.reactivex.disposables.Disposable

abstract class BasePresenter<out V: IMvpView>: IPresenter<V> {
    override lateinit var view: @UnsafeVariance V

    protected val disposableList = ArrayList<Disposable>()

    override fun onAttatch(view: @UnsafeVariance V) {
        this.view = view
    }

    override fun onDetach() {
        disposableList.forEach(Disposable::dispose)
        disposableList.clear()
    }
}