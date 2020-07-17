package com.iniudin.githubuserapp.network

import com.iniudin.githubuserapp.pojo.GithubProfile
import com.iniudin.githubuserapp.pojo.GithubSearch
import com.iniudin.githubuserapp.pojo.ItemsGithubSearch
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

interface GithubService {

    @GET("search/users")
    fun getSearhResult(@Query("q") username: String): Observable<GithubSearch>

    @GET("users/{username}")
    fun getProfile(@Path("username") username: String): Observable<GithubProfile>

    @GET("users/{username}/followers")
    fun getFollowersResult(@Path("username") username: String) : Observable<List<ItemsGithubSearch>>

    @GET("users/{username}/following")
    fun getFollowingResult(@Path("username") username: String) : Observable<List<ItemsGithubSearch>>

}