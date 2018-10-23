package com.me.guanpj.kotlinhub.base

import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy


abstract class BasePresenter<out V: IMvpView>: IPresenter<V> {
    private lateinit var weakView: WeakReference<V>
    private lateinit var proxyView: V

    fun addDisposable(disposable: Disposable) {
        getView().addDisposable(disposable)
    }

    override fun getView() : V = weakView.get()!!

    override fun onAttach(view: @UnsafeVariance V) {
        weakView = WeakReference(view)
        proxyView = Proxy.newProxyInstance(view.javaClass.classLoader,
                view.javaClass.interfaces, MyInvocationHandler(this.weakView.get())) as V
    }

    override fun onDetach() {
        weakView.clear()
    }

    fun isAttached(): Boolean = this.weakView.get() != null

    private inner class MyInvocationHandler<T>(private val target: T) : InvocationHandler {

        @Throws(InvocationTargetException::class, IllegalAccessException::class)
        override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
            return if (isAttached()) {
                method.invoke(target, args)
            } else null
        }
    }
}