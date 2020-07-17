package com.iniudin.githubuserapp.database

import android.net.Uri
import android.provider.BaseColumns
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.TABLE_NAME

object DatabaseContract {
    const val AUTHORITY = "com.iniudin.githubuserapp"
    private const val SCHEME = "content"

    class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_profile_table"
            const val ID = "id"
            const val AVATAR = "avatar_url"
            const val COMPANY = "company"
            const val FOLLOWERS = "followers"
            const val FOLLOWERSURL = "followers_url"
            const val LOCATION = "location"
            const val LOGIN = "login"
            const val NAME = "name"
            const val PUBLICREPOS = "public_repos"
        }
    }

    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()
}