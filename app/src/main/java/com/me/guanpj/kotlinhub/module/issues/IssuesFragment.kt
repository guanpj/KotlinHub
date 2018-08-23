package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
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

    override fun getLayoutResId(): Int = R.layout.fragment_issues

    override fun initView() {
    }

    override fun initDataAndEvent() {
    }
}