package com.me.guanpj.kotlinhub.entity

import com.me.guanpj.kotlinhub.util.anno.PoKo

@PoKo
data class PushEventCommit(var sha: String?,
                           //email&name
                           var author: User?,
                           var message: String?,
                           var isDistinct: Boolean,
                           var url: String?) {
}
