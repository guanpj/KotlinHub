package com.me.guanpj.kotlinhub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize
import java.util.*

@PoKo
@Parcelize
data class ReleaseAsset(var id: String,
                        var name: String,
                        var label: String?,
                        var uploader: User?,
                        @SerializedName("content_type") var contentType: String?,
                        var state: String?,
                        var size: Long,
                        var downloadCout: Int,
                        @SerializedName("created_at") var createdAt: Date?,
                        @SerializedName("updated_at") var updatedAt: Date?,
                        @SerializedName("browser_download_url") var downloadUrl: String? = null) : Parcelable {
    //    {
    //        "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/releases/assets/4824185",
    //        "id": 4824185,
    //        "name": "openhub_v1.0.2.apk",
    //        "label": null,
    //        "content_type": "application/vnd.android.package-archive",
    //        "state": "uploaded",
    //        "size": 4158236,
    //        "download_count": 3,
    //        "created_at": "2017-09-14T14:15:23Z",
    //        "updated_at": "2017-09-14T14:18:54Z",
    //        "browser_download_url": "https://github.com/ThirtyDegreesRay/OpenHub/releases/download/v1.0.2/openhub_v1.0.2.apk"
    //        "uploader": {
    //        "login": "ThirtyDegreesRay",
    //                "id": 9625508,
    //                "avatar_url": "https://avatars1.githubusercontent.com/u/9625508?v=4",
    //                "gravatar_id": "",
    //                "url": "https://api.github.com/users/ThirtyDegreesRay",
    //                "html_url": "https://github.com/ThirtyDegreesRay",
    //                "followers_url": "https://api.github.com/users/ThirtyDegreesRay/followers",
    //                "following_url": "https://api.github.com/users/ThirtyDegreesRay/following{/other_user}",
    //                "gists_url": "https://api.github.com/users/ThirtyDegreesRay/gists{/gist_id}",
    //                "starred_url": "https://api.github.com/users/ThirtyDegreesRay/starred{/owner}{/repo}",
    //                "subscriptions_url": "https://api.github.com/users/ThirtyDegreesRay/subscriptions",
    //                "organizations_url": "https://api.github.com/users/ThirtyDegreesRay/orgs",
    //                "repos_url": "https://api.github.com/users/ThirtyDegreesRay/repos",
    //                "events_url": "https://api.github.com/users/ThirtyDegreesRay/events{/privacy}",
    //                "received_events_url": "https://api.github.com/users/ThirtyDegreesRay/received_events",
    //                "type": "User",
    //                "site_admin": false
    //    },
    //    }
}
