package com.me.guanpj.kotlinhub.util

import android.webkit.MimeTypeMap

import java.util.regex.Pattern

/**
 * Created on 2017/8/19 17:24:29
 * Copied from Copyright (C) 2017 Kosh.
 * Modified by Copyright (C) 2017 ThirtyDegreesRay.
 */

object GitHubHelper {

    private val IMAGE_EXTENSIONS = arrayOf(".png", ".jpg", ".jpeg", ".gif", ".svg")

    private val MARKDOWN_EXTENSIONS = arrayOf(".md", ".mkdn", ".mdwn", ".mdown", ".markdown", ".mkd", ".mkdown", ".ron", ".rst", "adoc")

    private val ARCHIVE_EXTENSIONS = arrayOf(".zip", ".zipx", ".7z", "s7z", "zz", ".rar", ".tar.gz", ".tgz", ".tar.Z", ".tar.bz2", ".tbz2", ".tar.lzma", ".tlz", ".apk", ".jar", ".dmg", "ipa", "war", "cab", "dar", "aar")

    private val GITHUB_BASE_URL_PATTERN_STR = "(https://)?(http://)?(www.)?github.com"

    val REPO_FULL_NAME_PATTERN = Pattern.compile("([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*")
    private val USER_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*(/)?")
    private val REPO_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*(/)?")
    private val ISSUE_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*/issues/(\\d)*(/)?")
    private val RELEASES_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*/releases(/latest)?(/)?")
    private val RELEASE_TAG_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*/releases/tag/([^/])*(/)?")

    private val COMMIT_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR/([a-z]|[A-Z]|\\d|-)*/([a-z]|[A-Z]|\\d|-|\\.|_)*/commit(s)?/([a-z]|\\d)*(/)?")

    private val GITHUB_URL_PATTERN = Pattern.compile("$GITHUB_BASE_URL_PATTERN_STR(.)*")

    fun isImage(name: String?): Boolean {
        var name = name
        if (StringUtils.isBlank(name)) return false
        name = name!!.toLowerCase()
        for (value in IMAGE_EXTENSIONS) {
            val extension = MimeTypeMap.getFileExtensionFromUrl(name)
            if (extension != null && value.replace(".", "") == extension || name.endsWith(value))
                return true
        }
        return false
    }

    fun isMarkdown(name: String?): Boolean {
        var name = name
        if (StringUtils.isBlank(name)) return false
        name = name!!.toLowerCase()
        for (value in MARKDOWN_EXTENSIONS) {
            val extension = MimeTypeMap.getFileExtensionFromUrl(name)
            if (extension != null && value.replace(".", "") == extension ||
                    name.equals("README", ignoreCase = true) || name.endsWith(value))
                return true
        }
        return false
    }

    fun isArchive(name: String?): Boolean {
        var name = name
        if (StringUtils.isBlank(name)) return false
        name = name!!.toLowerCase()
        for (value in ARCHIVE_EXTENSIONS) {
            val extension = MimeTypeMap.getFileExtensionFromUrl(name)
            if (extension != null && value.replace(".", "") == extension || name.endsWith(value))
                return true
        }

        return false
    }

    fun getExtension(name: String?): String? {
        return if (StringUtils.isBlank(name)) null else MimeTypeMap.getFileExtensionFromUrl(name)
    }

    fun isUserUrl(url: String): Boolean {
        return USER_PATTERN.matcher(url).matches()
    }

    fun isRepoUrl(url: String): Boolean {
        return REPO_PATTERN.matcher(url).matches()
    }

    fun isIssueUrl(url: String): Boolean {
        return ISSUE_PATTERN.matcher(url).matches()
    }

    fun isGitHubUrl(url: String): Boolean {
        return GITHUB_URL_PATTERN.matcher(url).matches()
    }

    fun isReleasesUrl(url: String): Boolean {
        return RELEASES_PATTERN.matcher(url).matches()
    }

    fun isReleaseTagUrl(url: String): Boolean {
        return RELEASE_TAG_PATTERN.matcher(url).matches()
    }

    fun isCommitUrl(url: String): Boolean {
        return COMMIT_PATTERN.matcher(url).matches()
    }

    fun getUserFromUrl(url: String): String? {
        var url = url
        if (!isUserUrl(url)) return null
        if (url.endsWith("/")) url = url.substring(0, url.length - 1)
        return url.substring(url.lastIndexOf("/") + 1)
    }

    fun getRepoFullNameFromUrl(url: String): String? {
        var url = url
        if (!isRepoUrl(url)) return null
        if (url.endsWith("/")) url = url.substring(0, url.length - 1)
        return url.substring(url.indexOf("com/") + 4)
    }

}
