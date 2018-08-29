package com.me.guanpj.kotlinhub.module.feeds

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import com.chad.library.adapter.base.BaseViewHolder
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.entity.Event
import com.me.guanpj.kotlinhub.entity.EventPayload
import com.me.guanpj.kotlinhub.entity.EventPayload.*
import com.me.guanpj.kotlinhub.entity.EventType
import com.me.guanpj.kotlinhub.ext.loadWithGlide
import com.me.guanpj.kotlinhub.module.adapter.CommonListAdapter
import com.me.guanpj.kotlinhub.util.GitHubHelper
import com.me.guanpj.kotlinhub.util.StringUtils
import com.me.guanpj.kotlinhub.widget.EllipsizeLineSpan
import de.hdodenhof.circleimageview.CircleImageView

class FeedsAdapter : CommonListAdapter<Event>(R.layout.item_feeds) {
    override fun convert(holder: BaseViewHolder, item: Event) {
        holder.setText(R.id.user_name, item.actor.login)
        holder.getView<CircleImageView>(R.id.user_avatar).loadWithGlide(item.actor.avatar_url)
        holder.setText(R.id.time, StringUtils.getNewsTimeStr(mContext, item.createdAt!!))
        setActionAndDesc(holder, item)
    }

    fun setActionAndDesc(holder: BaseViewHolder, model: Event) {
        var actionStr: String? = null
        var descSpan: SpannableStringBuilder? = null
        val fullName = model.repo?.fullName
        val refType = model.payload?.refType
        val action = model.payload?.action

        when (model.type) {
            EventType.CommitCommentEvent -> {
                actionStr = String.format(mContext.getString(R.string.created_comment_on_commit), fullName)
                descSpan = SpannableStringBuilder(model.payload?.comment?.body)
            }
            EventType.CreateEvent -> if (EventPayload.RefType.repository.equals(refType)) {
                actionStr = String.format(mContext.getString(R.string.created_repo), fullName)
            } else if (EventPayload.RefType.branch.equals(refType)) {
                actionStr = String.format(mContext.getString(R.string.created_branch_at),
                        model.payload?.ref, fullName)
            } else if (EventPayload.RefType.tag.equals(refType)) {
                actionStr = String.format(mContext.getString(R.string.created_tag_at),
                        model.payload?.ref, fullName)
            }
            EventType.DeleteEvent -> if (EventPayload.RefType.branch.equals(refType)) {
                actionStr = String.format(mContext.getString(R.string.delete_branch_at),
                        model.payload?.ref, fullName)
            } else if (EventPayload.RefType.tag.equals(refType)) {
                actionStr = String.format(mContext.getString(R.string.delete_tag_at),
                        model.payload?.ref, fullName)
            }
            EventType.ForkEvent -> {
                val oriRepo = model.repo?.fullName
                val newRepo = model.actor.login + "/" + model.repo?.name
                actionStr = String.format(mContext.getString(R.string.forked_to), oriRepo, newRepo)
            }
            EventType.GollumEvent -> actionStr = action!! + " a wiki page "

            EventType.InstallationEvent -> actionStr = action!! + " an GitHub App "
            EventType.InstallationRepositoriesEvent -> actionStr = action!! + " repository from an installation "
            EventType.IssueCommentEvent -> {
                actionStr = String.format(mContext.getString(R.string.created_comment_on_issue),
                        model.payload?.issue?.number, model.repo?.fullName)
                descSpan = SpannableStringBuilder(model.payload?.comment?.body)
            }
            EventType.IssuesEvent -> {
                val issueEventStr = getIssueEventStr(action!!)
                actionStr = String.format(issueEventStr,
                        model.payload?.issue?.number, model.repo?.fullName)
                descSpan = SpannableStringBuilder(model.payload?.issue?.title)
            }

            EventType.MarketplacePurchaseEvent -> actionStr = action!! + " marketplace plan "
            EventType.MemberEvent -> {
                val memberEventStr = getMemberEventStr(action!!)
                actionStr = String.format(memberEventStr,
                        model.payload?.member?.login, fullName)
            }
            EventType.OrgBlockEvent -> {
                val orgBlockEventStr: String
                if (EventPayload.OrgBlockEventActionType.blocked.name.equals(action)) {
                    orgBlockEventStr = mContext.getString(R.string.org_blocked_user)
                } else {
                    orgBlockEventStr = mContext.getString(R.string.org_unblocked_user)
                }
                actionStr = String.format(orgBlockEventStr,
                        model.payload?.organization?.login,
                        model.payload?.blockedUser?.login)
            }
            EventType.ProjectCardEvent -> actionStr = action!! + " a project "
            EventType.ProjectColumnEvent -> actionStr = action!! + " a project "

            EventType.ProjectEvent -> actionStr = action!! + " a project "
            EventType.PublicEvent -> actionStr = String.format(mContext.getString(R.string.made_repo_public), fullName)
            EventType.PullRequestEvent -> actionStr = action + " pull request " + model.repo?.fullName
            EventType.PullRequestReviewEvent -> {
                val pullRequestReviewStr = getPullRequestReviewEventStr(action!!)
                actionStr = String.format(pullRequestReviewStr, fullName)
            }
            EventType.PullRequestReviewCommentEvent -> {
                val pullRequestCommentStr = getPullRequestReviewCommentEventStr(action!!)
                actionStr = String.format(pullRequestCommentStr, fullName)
                descSpan = SpannableStringBuilder(model.payload?.comment?.body)
            }

            EventType.PushEvent -> {
                val branch = model.payload?.getBranch()
                actionStr = String.format(mContext.getString(R.string.push_to), branch, fullName)

                descSpan = SpannableStringBuilder("")
                val count = model.payload?.commits?.size
                val maxLines = 4
                val max = if (count!! > maxLines) maxLines - 1 else count

                for (i in 0 until max) {
                    val commit = model.payload?.commits?.get(i)
                    if (i != 0) {
                        descSpan.append("\n")
                    }

                    val lastLength = descSpan.length
                    val sha = commit?.sha?.substring(0, 7)
                    descSpan.append(sha)
                    descSpan.setSpan(TextAppearanceSpan(mContext, R.style.Text_Link),
                            lastLength, lastLength + sha!!.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    descSpan.append(" ")
                    descSpan.append(getFirstLine(commit?.message))

                    descSpan.setSpan(EllipsizeLineSpan(if (i == count - 1) 0 else 0),
                            lastLength, descSpan.length, 0)
                }
                if (count > maxLines) {
                    descSpan.append("\n").append("...")
                }
            }
            EventType.ReleaseEvent -> actionStr = String.format(mContext.getString(R.string.published_release_at),
                    model.payload?.release?.tagName, fullName)
            EventType.WatchEvent
            -> actionStr = String.format(mContext.getString(R.string.starred_repo), fullName)
        }

        holder.setVisible(R.id.action, true)
        if (descSpan != null) {
            holder.setGone(R.id.desc, true)
            holder.setText(R.id.desc, descSpan)
        } else {
            holder.setGone(R.id.desc, false)
        }

        actionStr = StringUtils.upCaseFirstChar(actionStr)
        actionStr = actionStr ?: ""
        val span = SpannableStringBuilder(actionStr)
        val matcher = GitHubHelper.REPO_FULL_NAME_PATTERN.matcher(actionStr)
        while (matcher.find()) {
            span.setSpan(StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        holder.setText(R.id.action, span)
    }

    private fun getFirstLine(str: String?): String? {
        return if (str == null || !str.contains("\n")) str else str.substring(0, str.indexOf("\n"))
    }

    private fun getPullRequestReviewEventStr(action: String): String {
        val actionType = EventPayload.PullRequestReviewEventActionType.valueOf(action)
        when (actionType) {
            PullRequestReviewEventActionType.submitted -> return mContext.getString(R.string.submitted_pull_request_review_at)
            PullRequestReviewEventActionType.edited -> return mContext.getString(R.string.edited_pull_request_review_at)
            PullRequestReviewEventActionType.dismissed -> return mContext.getString(R.string.dismissed_pull_request_review_at)
            else -> return mContext.getString(R.string.submitted_pull_request_review_at)
        }
    }

    private fun getPullRequestReviewCommentEventStr(action: String): String {
        val actionType = EventPayload.PullRequestReviewCommentEventActionType.valueOf(action)
        when (actionType) {
            PullRequestReviewCommentEventActionType.created -> return mContext.getString(R.string.created_pull_request_comment_at)
            PullRequestReviewCommentEventActionType.edited -> return mContext.getString(R.string.edited_pull_request_comment_at)
            PullRequestReviewCommentEventActionType.deleted -> return mContext.getString(R.string.deleted_pull_request_comment_at)
            else -> return mContext.getString(R.string.created_pull_request_comment_at)
        }
    }

    private fun getMemberEventStr(action: String): String {
        val actionType = EventPayload.MemberEventActionType.valueOf(action)
        when (actionType) {
            MemberEventActionType.added -> return mContext.getString(R.string.added_member_to)
            MemberEventActionType.deleted -> return mContext.getString(R.string.deleted_member_at)
            MemberEventActionType.edited -> return mContext.getString(R.string.edited_member_at)
            else -> return mContext.getString(R.string.added_member_to)
        }
    }

    private fun getIssueEventStr(action: String): String {
        val actionType = EventPayload.IssueEventActionType.valueOf(action)
        when (actionType) {
            IssueEventActionType.assigned -> return mContext.getString(R.string.assigned_issue_at)
            IssueEventActionType.unassigned -> return mContext.getString(R.string.unassigned_issue_at)
            IssueEventActionType.labeled -> return mContext.getString(R.string.labeled_issue_at)
            IssueEventActionType.unlabeled -> return mContext.getString(R.string.unlabeled_issue_at)
            IssueEventActionType.opened -> return mContext.getString(R.string.opened_issue_at)

            IssueEventActionType.edited -> return mContext.getString(R.string.edited_issue_at)
            IssueEventActionType.milestoned -> return mContext.getString(R.string.milestoned_issue_at)
            IssueEventActionType.demilestoned -> return mContext.getString(R.string.demilestoned_issue_at)
            IssueEventActionType.closed -> return mContext.getString(R.string.closed_issue_at)
            IssueEventActionType.reopened -> return mContext.getString(R.string.reopened_issue_at)

            else -> return mContext.getString(R.string.opened_issue_at)
        }
    }
}