package com.me.guanpj.kotlinhub.util

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtil {
    fun <T> observableThreadTransformer(): ObservableTransformer<T, T> = ObservableTransformer { observable: Observable<T> ->
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> flowableThreadTransformer(): FlowableTransformer<T, T> = FlowableTransformer { flowable: Flowable<T> ->
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}