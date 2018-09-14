package com.me.guanpj.kotlinhub.module.main

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import com.me.guanpj.kotlinhub.module.feeds.FeedsFragment
import com.me.guanpj.kotlinhub.module.issues.MyIssuesPagerFragment
import com.me.guanpj.kotlinhub.module.pull_requests.PullRequestsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_appbar.*
import me.yokeyword.fragmentation.SupportFragment

class MainFragment : BaseFragment() {

    val FIRST = 0
    val SECOND = 1
    val THIRD = 2

    var mFragments = arrayOfNulls<SupportFragment>(3)
    var mCurrentFragmentIndex = 0

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun initView(view: View) {
        toolbar.inflateMenu(R.menu.menu_main)

        val firstFragment = findChildFragment(MyIssuesPagerFragment::class.java)

        if(firstFragment == null) {
            mFragments[FIRST] = FeedsFragment.newInstance()
            mFragments[SECOND] = MyIssuesPagerFragment.newInstance()
            mFragments[THIRD] = PullRequestsFragment.newInstance()

            loadMultipleRootFragment(R.id.frame_layout_content, FIRST, mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD])
        } else {
            mFragments[FIRST] = firstFragment
            mFragments[SECOND] = findChildFragment(MyIssuesPagerFragment::class.java)
            mFragments[THIRD] = findChildFragment(PullRequestsFragment::class.java)
        }
        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.feeds -> {
                    showHideFragment(mFragments[FIRST], mFragments[mCurrentFragmentIndex])
                    mCurrentFragmentIndex = FIRST
                    true
                }
                R.id.issues -> {
                    showHideFragment(mFragments[SECOND], mFragments[mCurrentFragmentIndex])
                    mCurrentFragmentIndex = SECOND
                    true
                }
                R.id.pullRequests -> {
                    showHideFragment(mFragments[THIRD], mFragments[mCurrentFragmentIndex])
                    mCurrentFragmentIndex = THIRD
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun initDataAndEvent() {
    }
}