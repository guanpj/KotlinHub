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
                            if (it.isEmpty()) view.onDataInitWithNothing() else {
                                correctEvent(it)
                                view.onDataInit(it)
                            }
                        },
                        { view.onDataInitWithError(it.message ?: it.toString()) })
                .let(disposableList::add)
    }

    fun refreshData() {
        listPage.loadFromFirst()
                .subscribe(
                        {
                            if (it.isEmpty()) view.onDataInitWithNothing() else {
                                correctEvent(it)
                                view.onDataRefresh(it)
                            }
                        },
                        { view.onDataRefreshWithError(it.message ?: it.toString()) }
                ).let(disposableList::add)
    }

    fun loadMore() {
        listPage.loadMore()
                .subscribe(
                        {
                            correctEvent(it)
                            view.onLoadMoreData(it)
                        },
                        { view.onLoadMoreDataWithError(it.message ?: it.toString()) }
                ).let(disposableList::add)
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