package com.me.guanpj.kotlinhub.module.feeds

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.data.remote.api.UserApi
import com.me.guanpj.kotlinhub.entity.Event
import javax.inject.Inject

class FeedsPresenter @Inject constructor() : BasePresenter<FeedsContract.View<Event>>(), FeedsContract.Presenter<Event> {

    @Inject
    lateinit var userApi: UserApi

    private val listPage by lazy {
        FeedsPage(userApi)
    }

    fun initData() {
        listPage.loadFromFirst()
                .subscribe(
                        { if (it.isEmpty()) view.onDataInitWithNothing() else view.onDataInit(it) },
                        {view.onDataInitWithError(it.message ?: it.toString()) })
                .let(disposableList::add)
    }

    fun refreshData() {
        listPage.loadFromFirst()
                .subscribe(
                        { if (it.isEmpty()) view.onDataInitWithNothing() else view.onDataRefresh(it) },
                        { view.onDataRefreshWithError(it.message ?: it.toString()) }
                ).let(disposableList::add)
    }

    fun loadMore() {
        listPage.loadMore()
                .subscribe(
                        { view.onMoreDataLoaded(it) },
                        { view.onMoreDataLoadedWithError(it.message ?: it.toString()) }
                ).let(disposableList::add)
    }
}