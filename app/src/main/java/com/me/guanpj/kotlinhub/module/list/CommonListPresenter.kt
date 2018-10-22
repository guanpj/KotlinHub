package com.me.guanpj.kotlinhub.module.list

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.widget.ListPage
import javax.inject.Inject

class CommonListPresenter<D, out V : CommonListFragment<D, CommonListPresenter<D, V>>> @Inject constructor() : BasePresenter<V>() {
    lateinit var listPage: ListPage<D>

    fun initData() {
        listPage.loadFromFirst()
                .subscribe(
                        { if (it.isEmpty()) getView()?.onDataInitWithNothing() else getView()?.onDataInit(it) },
                        {getView()?.onDataInitWithError(it.message ?: it.toString()) })
                .also { addDisposable(it) }
    }

    fun refreshData() {
        listPage.loadFromFirst()
                .subscribe(
                        { if (it.isEmpty()) getView()?.onDataInitWithNothing() else getView()?.onDataRefresh(it) },
                        { getView()?.onDataRefreshWithError(it.message ?: it.toString()) }
                ).also { addDisposable(it) }
    }

    fun loadMore() {
        listPage.loadMore()
                .subscribe(
                        { getView()?.onMoreDataLoaded(it) },
                        { getView()?.onMoreDataLoadedWithError(it.message ?: it.toString()) }
                ).also { addDisposable(it) }
    }

}