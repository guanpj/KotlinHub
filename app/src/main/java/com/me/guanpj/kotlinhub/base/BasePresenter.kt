package com.me.guanpj.kotlinhub.base

import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<out V: IMvpView>: IPresenter<V> {
    private lateinit var weakView: WeakReference<V>

    fun addDisposable(disposable: Disposable) {
        getView()?.addDisposable(disposable)
    }

    override fun getView() : V? = weakView.get()

    override fun onAttach(view: @UnsafeVariance V) {
        weakView = WeakReference(view)
    }

    override fun onDetach() {
        weakView.clear()
    }
}