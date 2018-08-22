package com.me.guanpj.kotlinhub.module.main

import android.os.Bundle
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.layout_appbar.*

class MainFragment : BaseFragment() {

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
    }

    override fun initDataAndEvent() {
    }
}