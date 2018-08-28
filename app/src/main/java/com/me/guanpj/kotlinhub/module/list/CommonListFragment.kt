package com.me.guanpj.kotlinhub.module.list

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseMvpFragment
import com.me.guanpj.kotlinhub.module.adapter.CommonListAdapter
import com.me.guanpj.kotlinhub.widget.view.CustomLoadMoreView
import kotlinx.android.synthetic.main.fragment_common_list.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import retrofit2.adapter.rxjava2.GitHubPaging

class CommonListFragment<D, out P : CommonListPresenter<D, CommonListFragment<D, P>>> : BaseMvpFragment<P>() {

    lateinit var adapter: CommonListAdapter<D>

    override fun getLayoutResId(): Int = R.layout.fragment_common_list

    override fun initView(view: View) {
        refreshView.setColorSchemeResources(R.color.material_red_700, R.color.material_yellow_700,
                R.color.material_green_700, R.color.material_blue_700)

        recyclerView.adapter = adapter
        adapter.setEnableLoadMore(true)
        adapter.setLoadMoreView(CustomLoadMoreView())
        adapter.setOnLoadMoreListener({ presenter::loadMore }, recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()

        refreshView.isRefreshing = true
        refreshView.onRefresh(presenter::refreshData)
    }

    override fun initDataAndEvent() {
        presenter.initData()
    }

    fun setLoadMoreEnable(isEnabled: Boolean) {
        adapter.setEnableLoadMore(isEnabled)
    }

    fun onDataInit(data: GitHubPaging<D>) {
        adapter.addData(data)
        if (data.isLast) adapter.loadMoreEnd()
        refreshView.isRefreshing = false
        dismissError()
    }

    fun onDataRefresh(data: GitHubPaging<D>) {
        onDataInit(data)
    }

    fun onDataInitWithNothing() {
        showError("No Data.")
        adapter.loadMoreEnd()
        refreshView.isRefreshing = false
        statusView.isClickable = false
    }

    fun onDataInitWithError(error: String) {
        showError(error)
        statusView.onClick {
            presenter.initData()
        }
    }

    fun onDataRefreshWithError(error: String) {
        if (adapter.data.isEmpty()) {
            showError(error)
            statusView.onClick {
                presenter.initData()
            }
        } else {
            toast(error)
        }
    }

    fun onMoreDataLoaded(data: GitHubPaging<D>) {
        adapter.replaceData(data)
        if (data.isLast) adapter.loadMoreEnd()
        dismissError()
    }

    fun onMoreDataLoadedWithError(error: String) {
        showError(error)
        statusView.onClick {
            presenter.initData()
        }
    }
}