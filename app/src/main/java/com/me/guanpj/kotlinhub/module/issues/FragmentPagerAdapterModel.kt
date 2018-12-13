package com.me.guanpj.kotlinhub.module.issues

import android.content.Context
import android.support.v4.app.Fragment
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.data.local.types.IssueState
import com.me.guanpj.kotlinhub.data.local.types.MyIssuesType

class FragmentPagerAdapterModel(var title: String, var fragment: Fragment) {
    companion object {
        fun buildForMyIssues(context: Context): List<FragmentPagerAdapterModel> {
            var list = ArrayList<FragmentPagerAdapterModel>()
            list.add(FragmentPagerAdapterModel(context.getString(R.string.created),
                    MyIssuesFragment.newInstance(IssueState.open, MyIssuesType.CREATED)))
            list.add(FragmentPagerAdapterModel(context.getString(R.string.assigned),
                    MyIssuesFragment.newInstance(IssueState.open, MyIssuesType.ASSIGNED)))
            list.add(FragmentPagerAdapterModel(context.getString(R.string.mentioned),
                    MyIssuesFragment.newInstance(IssueState.open, MyIssuesType.MENTIONED)))
            list.add(FragmentPagerAdapterModel(context.getString(R.string.participated),
                    MyIssuesFragment.newInstance(IssueState.open, MyIssuesType.PARTICIPATED)))
            return list
        }
    }
}
