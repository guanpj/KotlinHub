package com.me.guanpj.kotlinhub.module.feeds

import com.me.guanpj.kotlinhub.core.AccountManager
import com.me.guanpj.kotlinhub.data.remote.api.UserApi
import com.me.guanpj.kotlinhub.entity.Event
import com.me.guanpj.kotlinhub.widget.ListPage
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.GitHubPaging

class FeedsPage(private val userApi: UserApi) : ListPage<Event>() {
    override fun getData(page: Int): Observable<GitHubPaging<Event>> {
        return userApi.getNewsEvents(AccountManager.userName, page)
    }
}