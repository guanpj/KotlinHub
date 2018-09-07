package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment

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
    }
}