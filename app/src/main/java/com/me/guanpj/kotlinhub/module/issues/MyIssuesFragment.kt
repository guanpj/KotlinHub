package com.me.guanpj.kotlinhub.module.issues

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.fragment.BaseFragment
import com.me.guanpj.kotlinhub.data.local.types.IssueState
import com.me.guanpj.kotlinhub.data.local.types.MyIssuesType
import com.me.guanpj.kotlinhub.util.Constant

class MyIssuesFragment : BaseFragment() {

    companion object {
        fun newInstance(issueState: IssueState, myIssuesType: MyIssuesType): MyIssuesFragment {
            val fragment = MyIssuesFragment()
            val args = Bundle()
            args.putSerializable(Constant.EXTRA_ISSUE_STATE, issueState)
            args.putSerializable(Constant.EXTRA_MY_ISSUE_TYPE, myIssuesType)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_common_list

    override fun initView(view: View) {
    }

    override fun initDataAndEvent() {
    }
}