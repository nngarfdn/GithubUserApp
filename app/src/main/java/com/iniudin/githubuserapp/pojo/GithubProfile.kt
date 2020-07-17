package com.iniudin.githubuserapp.pojo

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_profile_table")
data class GithubProfile(
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String?,
    @ColumnInfo(name = "company")
    val company: String?,
    @ColumnInfo(name = "followers")
    val followers: Int?,
    @ColumnInfo(name = "followers_url")
    val following: Int?,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val id: Int?,
   @ColumnInfo(name = "location")
   @Nullable
    var location: String? ,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "public_repos")
    val public_repos: Int?
) : Parcelable {
    companion object {
        const val AUTHORITY = "com.iniudin.githubuserapp"
        const val SCHEME = "content"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath("favorite_profile_table")
            .build()
    }
}

