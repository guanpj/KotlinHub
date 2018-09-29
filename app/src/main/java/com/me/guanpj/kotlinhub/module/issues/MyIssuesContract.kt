package com.me.guanpj.kotlinhub.module.issues

import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.IPresenter
import retrofit2.adapter.rxjava2.GitHubPaging

interface MyIssuesContract {
    interface View<D> : IMvpView {
        fun onDataInit(data: GitHubPaging<D>)

        fun onDataRefresh(data: GitHubPaging<D>)

        fun onDataInitWithNothing()

        fun onDataInitWithError(error: String)

        fun onDataRefreshWithError(error: String)

        fun onMoreDataLoaded(data: GitHubPaging<D>)

        fun onMoreDataLoadedWithError(error: String)
    }

    interface Presenter<D> : IPresenter<View<D>> {

    }
}