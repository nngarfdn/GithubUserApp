package com.iniudin.githubuserapp.network


import com.google.gson.GsonBuilder
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory



object GithubClient {
    fun initRetrofit(): GithubService {
        val gson = GsonBuilder().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.github.com/")
            .build()
        return retrofit.create(
            GithubService::class.java
        )
    }
}