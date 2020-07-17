package com.iniudin.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iniudin.githubuserapp.network.GithubClient
import com.iniudin.githubuserapp.pojo.GithubProfile
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ProfileViewModel : ViewModel() {

    private var searchData: MutableLiveData<GithubProfile> = MutableLiveData()

    fun setProfile(username: String) {
        val service = GithubClient.initRetrofit()

        service.getProfile(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                searchData.value = user
            }, { error ->
                error.message?.let { Log.e("Error", it) }
            })
    }

    fun getProfileResutl(): LiveData<GithubProfile> {
        return searchData
    }

}