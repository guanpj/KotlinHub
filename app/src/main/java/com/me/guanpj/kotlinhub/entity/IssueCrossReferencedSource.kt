package com.me.guanpj.kotlinhub.entity
class IssueCrossReferencedSource(var type: Type?,
                                 var issue: Issue?) {
    enum class Type {
        issue, commit
    }
}
