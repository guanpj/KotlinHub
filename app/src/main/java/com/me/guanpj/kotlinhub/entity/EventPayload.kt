package com.me.guanpj.kotlinhub.entity

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import java.util.*

data class EventPayload(@SerializedName("push_id")
                        var pushId: String,
                        var size: Int,
                        @SerializedName("distinct_size")
                        var distinctSize: Int,
                        var ref: String?,//PushEvent&CreateEvent
                        var head: String?,
                        var before: String?,
                        var commits: ArrayList<PushEventCommit>?,

                        //WatchEvent&PullRequestEvent
                        var action: String?,

                        //CreateEvent
                        @SerializedName("ref_type") var refType: RefType?,
                        @SerializedName("master_branch") var masterBranch: String?,
                        var description: String?,
                        @SerializedName("pusher_type") var pusherType: String?,

                        //ReleaseEvent
                        var release: Release?,
                        //IssueCommentEvent
                        var issue: Issue?,
                        var comment: IssueEvent?,

                        //MemberEvent
                        var member: User?,

                        var organization: User?,
                        @SerializedName("blocked_user") var blockedUser: User?) {

    enum class RefType {
        repository, branch, tag
    }

    enum class IssueEventActionType {
        assigned, unassigned, labeled, unlabeled, opened,
        edited, milestoned, demilestoned, closed, reopened
    }

    enum class MemberEventActionType {
        added, deleted, edited
    }

    enum class OrgBlockEventActionType {
        blocked, unblocked
    }

    enum class PullRequestReviewCommentEventActionType {
        created, edited, deleted
    }

    enum class PullRequestReviewEventActionType {
        submitted, edited, dismissed
    }

    fun getBranch(): String? =
            if (TextUtils.isEmpty(ref)) null else ref!!.substring(ref!!.lastIndexOf("/") + 1)
}