package com.me.guanpj.kotlinhub.module.feeds

import android.os.Bundle
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.layout_appbar.*

class FeedsFragment : BaseFragment() {

    companion object {
        fun newInstance(): FeedsFragment {
            val args = Bundle()
            val fragment = FeedsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_feeds

    override fun initView() {
        toolbar.inflateMenu(R.menu.menu_main)
    }

    override fun initDataAndEvent() {
    }
}