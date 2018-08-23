package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize
import java.util.*

@PoKo
@Parcelize
data class Repository(var id: Int,
                        var name: String,
                        @SerializedName("full_name") var fullName: String,
                        @SerializedName("private") var repPrivate: Boolean,
                        @SerializedName("html_url") var htmlUrl: String,
                        var description: String, var language: String, var owner: User,
                        @SerializedName("default_branch") var defaultBranch: String,
                        @SerializedName("created_at") var createdAt: Date,
                        @SerializedName("updated_at") var updatedAt: Date,
                        @SerializedName("pushed_at") var pushedAt: Date,
                        @SerializedName("git_url") var gitUrl: String,
                        @SerializedName("ssh_url") var sshUrl: String,
                        @SerializedName("clone_url") var cloneUrl: String,
                        @SerializedName("svn_url") var svnUrl: String,
                        var size: Int,
                        @SerializedName("stargazers_count") var stargazersCount: Int,
                        @SerializedName("watchers_count") var watchersCount: Int,
                        @SerializedName("forks_count") var forksCount: Int,
                        @SerializedName("open_issues_count") var openIssuesCount: Int,
                        @SerializedName("subscribers_count") var subscribersCount: Int,

                        var isFork: Boolean = false,
                        var parent: Repository? = null,
                        var permissions: RepositoryPermissions?,

                        @SerializedName("has_issues") var isHasIssues: Boolean,
                        @SerializedName("has_projects") var isHasProjects: Boolean,
                        @SerializedName("has_downloads") var isHasDownloads: Boolean,
                        @SerializedName("has_wiki") var isHasWiki: Boolean,
                        @SerializedName("has_pages") var isHasPages: Boolean,

                        var sinceStargazersCount: Int,
                        var since: TrendingSince?) : Parcelable {
    enum class TrendingSince {
        Daily, Weekly, Monthly
    }
}