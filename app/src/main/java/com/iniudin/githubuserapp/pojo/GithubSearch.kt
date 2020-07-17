package com.iniudin.githubuserapp.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GithubSearch(
    val incomplete_results: Boolean,
    val items: List<ItemsGithubSearch>,
    val total_count: Long
)

@Parcelize
data class ItemsGithubSearch(
    val login: String,
    val id: Long,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
    val score: Double
):Parcelable

