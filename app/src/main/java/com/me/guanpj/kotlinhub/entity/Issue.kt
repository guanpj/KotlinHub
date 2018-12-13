package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import java.util.*

@PoKo
data class Issue(var id: String?,
                 var number: Int,
                 var title: String?,
                 var state: IssueState?,
                 var isLocked: Boolean,
                 @SerializedName("comments") var commentNum: Int,
                 @SerializedName("created_at") var createdAt: Date?,
                 @SerializedName("updated_at") var updatedAt: Date?,
                 @SerializedName("closed_at") var closedAt: Date?,
                 var body: String?,
                 @SerializedName("body_html") var bodyHtml: String?,

                 var user: User?,
                 @SerializedName("author_association") var authorAssociation: IssueAuthorAssociation?,
                 @SerializedName("repository_url") var repoUrl: String?,
                 @SerializedName("html_url") var htmlUrl: String?,

                 var labels: ArrayList<Label>?,
                 var assignee: User?,
                 var assignees: ArrayList<User>?,
                 var milestone: Milestone?,
                 @SerializedName("closed_by") var closedBy: User? = null) {

    val repoName: String?
        get() = if (!TextUtils.isEmpty(repoUrl) && repoUrl!!.contains("/"))
            repoUrl!!.substring(repoUrl!!.lastIndexOf("/") + 1)
        else
            null

    val repoFullName: String?
        get() = if (!TextUtils.isEmpty(repoUrl) && repoUrl!!.contains("repos/"))
            repoUrl!!.substring(repoUrl!!.lastIndexOf("repos/") + 6)
        else
            null

    val repoAuthorName: String?
        get() = if (!TextUtils.isEmpty(repoUrl) && repoUrl!!.contains("repos/"))
            repoUrl!!.substring(repoUrl!!.lastIndexOf("repos/") + 6, repoUrl!!.lastIndexOf("/"))
        else
            null

    enum class IssueState {
        open, closed
    }

    enum class IssueAuthorAssociation {
        OWNER, CONTRIBUTOR, NONE
    }
}
