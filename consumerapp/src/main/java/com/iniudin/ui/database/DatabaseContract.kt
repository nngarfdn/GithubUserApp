package com.iniudin.ui.database

import android.provider.BaseColumns

object DatabaseContract {

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

}