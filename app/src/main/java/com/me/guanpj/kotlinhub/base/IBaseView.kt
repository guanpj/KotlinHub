package com.me.guanpj.kotlinhub.base

import io.reactivex.disposables.Disposable

interface IBaseView {
    fun addDisposable(disposable: Disposable)

    fun removeDisposable(disposable: Disposable)

    fun deleteDisposable(disposable: Disposable)

    fun showDialog()

    fun hideDialog()
}