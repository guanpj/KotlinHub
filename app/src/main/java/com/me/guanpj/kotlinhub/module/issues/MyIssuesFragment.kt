package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import com.me.guanpj.kotlinhub.data.local.types.MyIssuesType
import com.me.guanpj.kotlinhub.entity.Issue

class MyIssuesFragment : BaseFragment() {

    companion object {
        fun newInstance(issueState: Issue.IssueState, created: MyIssuesType): MyIssuesFragment {
            val args = Bundle()
            val fragment = MyIssuesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initDataAndEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}