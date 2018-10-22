package com.me.guanpj.kotlinhub.module.feeds

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.data.remote.api.UserApi
import com.me.guanpj.kotlinhub.entity.Event
import com.me.guanpj.kotlinhub.entity.User
import retrofit2.adapter.rxjava2.GitHubPaging
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
                        {
                            if (it.isEmpty()) getView()?.onDataInitWithNothing() else {
                                correctEvent(it)
                                getView()?.onDataInit(it)
                            }
                        },
                        { getView()?.onDataInitWithError(it.message ?: it.toString()) })
                .also { addDisposable(it) }
    }

    fun refreshData() {
        listPage.loadFromFirst()
                .subscribe(
                        {
                            if (it.isEmpty()) getView()?.onDataInitWithNothing() else {
                                correctEvent(it)
                                getView()?.onDataRefresh(it)
                            }
                        },
                        { getView()?.onDataRefreshWithError(it.message ?: it.toString()) }
                ).also { addDisposable(it) }
    }

    fun loadMore() {
        listPage.loadMore()
                .subscribe(
                        {
                            correctEvent(it)
                            getView()?.onLoadMoreData(it)
                        },
                        { getView()?.onLoadMoreDataWithError(it.message ?: it.toString()) }
                ).also { addDisposable(it) }
    }

    private fun correctEvent(events: GitHubPaging<Event>) {
        for (event in events) {
            event.actor.type = User.UserType.User.toString()
            event.org?.type = User.UserType.Organization.toString()
            if (event.repo != null) {
                val fullName = event.repo!!.name
                event.repo!!.fullName = fullName
                event.repo!!.name = fullName.substring(fullName.indexOf("/") + 1)
            }
        }
    }
}