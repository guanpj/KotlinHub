package com.me.guanpj.kotlinhub.module.list

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.widget.ListPage
import javax.inject.Inject

class CommonListPresenter<D, out V : CommonListFragment<D, CommonListPresenter<D, V>>> @Inject constructor() : BasePresenter<V>() {
    lateinit var listPage: ListPage<D>

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