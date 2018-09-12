package com.me.guanpj.kotlinhub.module.issues

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

class FragmentAdapter(fm: FragmentManager, private val mTitles: List<String>) : FragmentPagerAdapter(fm) {
    private var mFragments: List<Fragment>? = null

    fun setFragments(fragments: List<Fragment>) {
        mFragments = fragments
    }

    override fun getItem(position: Int): Fragment? = mFragments!![position]

    override fun getCount(): Int = mFragments!!.size

    override fun getPageTitle(position: Int): String? = mTitles[position]

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    private fun initFragments() {

    }
}
