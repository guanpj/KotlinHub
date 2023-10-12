package com.me.guanpj.kotlinhub.entity

import com.google.gson.annotations.SerializedName
import java.util.*

enum class EventType {

    CommitCommentEvent,
    CreateEvent,
    /**
     * Represents a deleted branch or tag.
     */
    DeleteEvent,
    ForkEvent,
    /**
     * Triggered when a Wiki page is created or updated.
     */
    GollumEvent,


    /**
     * Triggered when a GitHub App has been installed or uninstalled.
     */
    InstallationEvent,
    /**
     * Triggered when a repository is added or removed from an installation.
     */
    InstallationRepositoriesEvent,
    IssueCommentEvent,
    IssuesEvent,


    /**
     * Triggered when a user purchases, cancels, or changes their GitHub Marketplace plan.
     */
    MarketplacePurchaseEvent,
    /**
     * Triggered when a user is added or removed as a collaborator to a repository, or has their permissions changed.
     */
    MemberEvent,
    /**
     * Triggered when an organization blocks or unblocks a user.
     */
    OrgBlockEvent,
    /**
     * Triggered when a project card is created, updated, moved, converted to an issue, or deleted.
     */
    ProjectCardEvent,
    /**
     * Triggered when a project column is created, updated, moved, or deleted.
     */
    ProjectColumnEvent,


    /**
     * Triggered when a project is created, updated, closed, reopened, or deleted.
     */
    ProjectEvent,
    /**
     * made repository public
     */
    PublicEvent,
    PullRequestEvent,
    /**
     * Triggered when a pull request review is submitted into a non-pending state, the body is edited, or the review is dismissed.
     */
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,


    PushEvent,
    ReleaseEvent,
    WatchEvent,

    //Events of this type are not visible in timelines. These events are only used to trigger hooks.
    DeploymentEvent,
    DeploymentStatusEvent,
    MembershipEvent,
    MilestoneEvent,
    OrganizationEvent,
    PageBuildEvent,
    RepositoryEvent,
    StatusEvent,
    TeamEvent,
    TeamAddEvent,
    LabelEvent,

    //Events of this type are no longer delivered, but it's possible that they exist in timelines
    // of some users. You cannot createForRepo webhooks that listen to these events.
    DownloadEvent,
    FollowEvent,
    ForkApplyEvent,
    GistEvent

}
data class Event(var id: String,
                 var type: EventType,
                 var actor: User,
                 var repo: Repository?,
                 var org: User?,
                 var payload: EventPayload?,
                 @SerializedName("public") var isPublic: Boolean,
                 @SerializedName("created_at") var createdAt: Date?) {

}