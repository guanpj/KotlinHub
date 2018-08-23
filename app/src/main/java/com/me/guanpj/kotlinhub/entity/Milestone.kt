package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize
import java.util.*

@PoKo
@Parcelize
class Milestone(var id: Long,
                var number: Int,
                var title: String?,
                var description: String?,
                var creator: User?,

                @SerializedName("open_issues") var openIssues: Int,
                @SerializedName("closed_issues") var closedIssues: Int,
                var state: State?,

                @SerializedName("created_at") var createdAt: Date?,
                @SerializedName("updated_at") var updatedAt: Date?,
                @SerializedName("due_on") var dueOn: Date?,
                @SerializedName("closed_at") var closedAt: Date?) : Parcelable {
    enum class State {
        OPEN, CLOSED
    }
}
