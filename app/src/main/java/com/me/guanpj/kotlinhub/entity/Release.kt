package com.me.guanpj.kotlinhub.entity

import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import java.util.*

@PoKo
data class Release(var id: String?,
                   @SerializedName("tag_name") var tagName: String?,
                   @SerializedName("target_commitish") var targetCommitish: String?,
                   var name: String?,
                   var body: String?,
                   @SerializedName("body_html") var bodyHtml: String?,
                   @SerializedName("tarball_url") var tarballUrl: String?,
                   @SerializedName("zipball_url")
                   var zipballUrl: String?,
                   var isDraft: Boolean,
                   @SerializedName("prerelease") var isPreRelease: Boolean,
                   @SerializedName("created_at") var createdAt: Date?,
                   @SerializedName("published_at") var publishedAt: Date?,

                   var author: User?,
                   var assets: List<ReleaseAsset>?) {

    //    {
    //        "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/releases/7754776",
    //        "assets_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/releases/7754776/assets",
    //        "upload_url": "https://uploads.github.com/repos/ThirtyDegreesRay/OpenHub/releases/7754776/assets{?name,label}",
    //        "html_url": "https://github.com/ThirtyDegreesRay/OpenHub/releases/tag/v1.0.2",
    //        "id": 7754776,
    //        "tag_name": "v1.0.2",
    //        "target_commitish": "master",
    //        "name": "v1.0.2",
    //        "draft": false,
    //        "author": {},
    //        "prerelease": false,
    //            "created_at": "2017-09-14T14:11:05Z",
    //            "published_at": "2017-09-14T14:33:40Z",
    //            "assets": [],
    //        "tarball_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/tarball/v1.0.2",
    //            "zipball_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/zipball/v1.0.2",
    //            "body": "* 1.fix bug when bundle data is too large.\r\n* 2.fix bug when authorized is unable."
    //    }

}
