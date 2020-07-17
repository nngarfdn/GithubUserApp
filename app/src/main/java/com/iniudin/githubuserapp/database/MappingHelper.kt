package com.iniudin.githubuserapp.database

import android.database.Cursor
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.AVATAR
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.COMPANY
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERS
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERSURL
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.ID
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOCATION
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOGIN
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.NAME
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.PUBLICREPOS
import com.iniudin.githubuserapp.pojo.GithubProfile

object MappingHelper {

    fun mapCursorToArrayList(gitCursor: Cursor?): ArrayList<GithubProfile> {
        val gitList = ArrayList<GithubProfile>()
        gitCursor?.apply {
            while (moveToNext()) {
                val avatar = getString(getColumnIndexOrThrow(AVATAR))
                val company = getString(getColumnIndexOrThrow(COMPANY))
                val follower = getInt(getColumnIndexOrThrow(FOLLOWERS))
                val following =
                    getInt(getColumnIndexOrThrow(FOLLOWERSURL))
                val id = getInt(getColumnIndexOrThrow(ID))
                val location =
                    getString(getColumnIndexOrThrow(LOCATION))
                val login = getString(getColumnIndexOrThrow(LOGIN))
                val name = getString(getColumnIndexOrThrow(NAME))
                val repos = getInt(getColumnIndexOrThrow(PUBLICREPOS))

                gitList.add(
                    GithubProfile(
                        avatar,
                        company,
                        follower,
                        following,
                        id,
                        location,
                        login,
                        name,
                        repos
                    )
                )
            }
        }
        return gitList
    }


}