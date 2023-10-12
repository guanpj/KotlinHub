package com.me.guanpj.kotlinhub.entity

import com.google.gson.annotations.SerializedName
import java.util.*
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
                @SerializedName("closed_at") var closedAt: Date?) {
    enum class State {
        OPEN, CLOSED
    }
}
