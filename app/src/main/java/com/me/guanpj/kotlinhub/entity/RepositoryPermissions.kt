package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize

@PoKo
@Parcelize
data class RepositoryPermissions(var admin: Boolean, var push: Boolean, var pull: Boolean) : Parcelable {
}