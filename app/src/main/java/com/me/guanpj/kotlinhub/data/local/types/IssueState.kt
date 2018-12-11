package com.me.guanpj.kotlinhub.data.local.types

import android.support.annotation.StringRes
import com.me.guanpj.kotlinhub.R

enum class IssueState (@StringRes status: Int) {
    `open`(R.string.opened),
    closed(R.string.closed),
    all(R.string.all);

    @get:StringRes
    var status: Int = 0
        internal set

    init {
        this.status = status
    }
}