package com.me.guanpj.kotlinhub.module.issues

import android.content.Context
import android.support.v4.app.Fragment
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.data.local.types.MyIssuesType
import com.me.guanpj.kotlinhub.entity.Issue

class FragmentPagerAdapterModel(var title: String, var fragment: Fragment) {
    fun buildForMyIssues(context: Context): List<FragmentPagerAdapterModel> {
        var list = ArrayList<FragmentPagerAdapterModel>()
        list.add(FragmentPagerAdapterModel(context.getString(R.string.created),
                MyIssuesFragment.newInstance(Issue.IssueState.open, MyIssuesType.CREATED)))
        list.add(FragmentPagerAdapterModel(context.getString(R.string.created),
                MyIssuesFragment.newInstance(Issue.IssueState.open, MyIssuesType.ASSIGNED)))
        list.add(FragmentPagerAdapterModel(context.getString(R.string.created),
                MyIssuesFragment.newInstance(Issue.IssueState.open, MyIssuesType.MENTIONED)))
        list.add(FragmentPagerAdapterModel(context.getString(R.string.created),
                MyIssuesFragment.newInstance(Issue.IssueState.open, MyIssuesType.PARTICIPATED)))
        return list
    }
}