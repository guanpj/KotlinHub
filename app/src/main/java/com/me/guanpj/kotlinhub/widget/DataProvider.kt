package com.me.guanpj.kotlinhub.widget

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.GitHubPaging

interface DataProvider<D> {
    fun getData(page: Int): Observable<GitHubPaging<D>>
}