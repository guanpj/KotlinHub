package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize

@PoKo
@Parcelize
data class PushEventCommit(var sha: String?,
                           //email&name
                           var author: User?,
                           var message: String?,
                           var isDistinct: Boolean,
                           var url: String?) : Parcelable {
}
