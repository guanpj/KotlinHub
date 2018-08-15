package com.me.guanpj.kotlinhub.base

interface IPresenter<out V : IMvpView<IPresenter<V>>> {
    val view: V

    fun onAttatch(view: @UnsafeVariance V)

    fun onDetach()
}

interface IMvpView<out P : IPresenter<IMvpView<P>>> {
    val presenter: P
}