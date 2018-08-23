package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize

@PoKo
@Parcelize
class IssueCrossReferencedSource(var type: Type?,
                                 var issue: Issue?) : Parcelable {
    enum class Type {
        issue, commit
    }
}
