package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import com.me.guanpj.kotlinhub.module.pull_requests.PullRequestsFragment
import kotlinx.android.synthetic.main.fragment_common_tab.*
import java.util.*

class IssuesFragment : BaseFragment() {

    companion object {
        fun newInstance(): IssuesFragment {
            val args = Bundle()
            val fragment = IssuesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_common_tab

    override fun initView(view: View) {

    }

    override fun initDataAndEvent() {
        val pageTitles = ArrayList<String>()
        pageTitles.add("热门话题")
        pageTitles.add("科技动态")
        pageTitles.add("开发者资讯")
        pageTitles.add("区块链快讯")

        val fragments = ArrayList<Fragment>()
        fragments.add(PullRequestsFragment.newInstance())
        fragments.add(PullRequestsFragment.newInstance())
        fragments.add(PullRequestsFragment.newInstance())
        fragments.add(PullRequestsFragment.newInstance())

        val adapter = FragmentAdapter(childFragmentManager, pageTitles)
        adapter.setFragments(fragments)
        pager.adapter = adapter
        /*for (i in pageTitles.indices) {
            tabs.addTab(tabs.newTab().setText(pageTitles[i]))
        }*/
        tabs.setTabsFromPagerAdapter(adapter)
        tabs.setupWithViewPager(pager)
        tabs.tabMode = TabLayout.MODE_FIXED
    }
}