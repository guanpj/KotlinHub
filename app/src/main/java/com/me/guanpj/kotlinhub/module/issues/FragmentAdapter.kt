package com.me.guanpj.kotlinhub.module.issues

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

class FragmentAdapter(fm: FragmentManager, private val mFragments: List<FragmentPagerAdapterModel>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = mFragments.get(position).fragment

    override fun getCount(): Int = mFragments!!.size

    override fun getPageTitle(position: Int): String? = mFragments.get(position).title

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    private fun initFragments() {

    }
}
