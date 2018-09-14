package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_common_tab.*

class MyIssuesPagerFragment : BaseFragment() {

    companion object {
        fun newInstance(): MyIssuesPagerFragment {
            val args = Bundle()
            val fragment = MyIssuesPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_common_tab

    override fun initView(view: View) {

    }

    override fun initDataAndEvent() {
        val adapter = FragmentAdapter(childFragmentManager, FragmentPagerAdapterModel.buildForMyIssues(activity!!))
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
        tabs.tabMode = TabLayout.MODE_SCROLLABLE


    }
}