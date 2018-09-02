package com.me.guanpj.kotlinhub.module.feeds

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseMvpFragment
import com.me.guanpj.kotlinhub.entity.Event
import com.me.guanpj.kotlinhub.widget.view.CustomLoadMoreView
import kotlinx.android.synthetic.main.fragment_common_list.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import retrofit2.adapter.rxjava2.GitHubPaging

class FeedsFragment : BaseMvpFragment<FeedsPresenter>(), FeedsContract.View<Event> {

    val adapter by lazy {
        FeedsAdapter()
    }

    companion object {
        fun newInstance(): FeedsFragment {
            val args = Bundle()
            val fragment = FeedsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View) {
        refreshView.setColorSchemeResources(R.color.material_red_700, R.color.material_yellow_700,
                R.color.material_green_700, R.color.material_blue_700)

        recyclerView.adapter = adapter
        adapter.setEnableLoadMore(true)
        adapter.setLoadMoreView(CustomLoadMoreView())
        adapter.setNotDoAnimationCount(3)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        adapter.setOnLoadMoreListener({ run { presenter.loadMore() } }, recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()

        refreshView.isRefreshing = true
        refreshView.onRefresh(presenter::refreshData)
    }

    override fun initDataAndEvent() {
        presenter.initData()
        adapter.setOnItemChildClickListener { adapter, view, pos ->
            Toast.makeText(context, "abcdddd", Toast.LENGTH_SHORT).show()
        }
        adapter.setOnItemClickListener { adapter, view, position -> Toast.makeText(context, "abc", Toast.LENGTH_SHORT).show() }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_common_list

    override fun onDataInit(data: GitHubPaging<Event>) {
        adapter.replaceData(data)
        if (data.isLast) adapter.loadMoreEnd()
        refreshView.isRefreshing = false
        dismissError()
    }

    override fun onDataRefresh(data: GitHubPaging<Event>) {
        onDataInit(data)
    }

    override fun onDataInitWithNothing() {
        showError("No Data.")
        adapter.loadMoreEnd()
        refreshView.isRefreshing = false
        statusView.isClickable = false
    }

    override fun onDataInitWithError(error: String) {
        showError(error)
        statusView.onClick {
            presenter.initData()
        }
    }

    override fun onDataRefreshWithError(error: String) {
        if (adapter.data.isEmpty()) {
            showError(error)
            statusView.onClick {
                presenter.initData()
            }
        } else {
            toast(error)
        }
    }

    override fun onMoreDataLoaded(data: GitHubPaging<Event>) {
        adapter.addData(data)
        if (data.isLast) adapter.loadMoreEnd()
        dismissError()
    }

    override fun onMoreDataLoadedWithError(error: String) {
        showError(error)
        statusView.onClick {
            presenter.initData()
        }
    }
}