package com.me.guanpj.kotlinhub.entity

import com.me.guanpj.kotlinhub.util.anno.PoKo

@PoKo
class IssueCrossReferencedSource(var type: Type?,
                                 var issue: Issue?) {
    enum class Type {
        issue, commit
    }
}
