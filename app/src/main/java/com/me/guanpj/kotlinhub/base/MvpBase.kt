package com.me.guanpj.kotlinhub.base

interface IPresenter<out V : IMvpView> {
    fun getView(): V?

    fun onAttach(view: @UnsafeVariance V)

    fun onDetach()
}

interface IMvpView : IBaseView  {
}