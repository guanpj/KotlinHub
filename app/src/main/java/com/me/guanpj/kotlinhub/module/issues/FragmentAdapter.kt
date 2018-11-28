package com.me.guanpj.kotlinhub.module.issues

import androidx.core.app.Fragment
import androidx.core.app.FragmentManager
import androidx.core.app.FragmentPagerAdapter
import androidx.core.view.PagerAdapter

class FragmentAdapter(fm: FragmentManager, private val mFragments: List<FragmentPagerAdapterModel>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = mFragments.get(position).fragment

    override fun getCount(): Int = mFragments!!.size

    override fun getPageTitle(position: Int): String? = mFragments.get(position).title

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    private fun initFragments() {

    }
}
