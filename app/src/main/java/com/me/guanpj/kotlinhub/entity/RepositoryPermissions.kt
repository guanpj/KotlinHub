package com.me.guanpj.kotlinhub.entity

import com.me.guanpj.kotlinhub.util.anno.PoKo

@PoKo
data class RepositoryPermissions(var admin: Boolean, var push: Boolean, var pull: Boolean) {
}