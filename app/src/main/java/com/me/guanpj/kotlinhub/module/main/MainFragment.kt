package com.me.guanpj.kotlinhub.module.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import com.me.guanpj.kotlinhub.module.feeds.FeedsFragment
import com.me.guanpj.kotlinhub.module.issues.IssuesFragment
import com.me.guanpj.kotlinhub.module.pull_requests.PullRequestsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_appbar.*
import me.yokeyword.fragmentation.SupportFragment

class MainFragment : BaseFragment() {

    val FIRST = 0
    val SECOND = 1
    val THIRD = 2

    var mFragments = arrayOfNulls<SupportFragment>(3)

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun initView() {
        toolbar.inflateMenu(R.menu.menu_main)

        val firstFragment = findChildFragment(IssuesFragment::class.java)

        if(firstFragment == null) {
            mFragments[FIRST] = FeedsFragment.newInstance()
            mFragments[SECOND] = IssuesFragment.newInstance()
            mFragments[THIRD] = PullRequestsFragment.newInstance()

            loadMultipleRootFragment(R.id.frame_layout_content, FIRST, mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD])
        } else {
            mFragments[FIRST] = firstFragment
            mFragments[SECOND] = findChildFragment(IssuesFragment::class.java)
            mFragments[THIRD] = findChildFragment(PullRequestsFragment::class.java)
        }

        bnv_main.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
                it.itemId
        })
    }

    override fun initDataAndEvent() {
    }
}