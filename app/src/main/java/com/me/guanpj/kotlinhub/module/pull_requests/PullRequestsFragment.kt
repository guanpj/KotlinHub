package com.me.guanpj.kotlinhub.module.pull_requests

import android.os.Bundle
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.layout_appbar.*

class PullRequestsFragment : BaseFragment() {

    companion object {
        fun newInstance(): PullRequestsFragment {
            val args = Bundle()
            val fragment = PullRequestsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_pull_requests

    override fun initView() {
        toolbar.inflateMenu(R.menu.menu_main)
    }

    override fun initDataAndEvent() {
    }
}