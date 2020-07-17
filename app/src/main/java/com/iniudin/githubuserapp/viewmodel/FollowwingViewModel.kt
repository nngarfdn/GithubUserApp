package com.iniudin.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iniudin.githubuserapp.network.GithubClient
import com.iniudin.githubuserapp.pojo.ItemsGithubSearch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class FollowwingViewModel : ViewModel() {

    private var searchData: MutableLiveData<List<ItemsGithubSearch>> = MutableLiveData()

    fun setFollowing(username: String) {
        val service = GithubClient.initRetrofit()

        service.getFollowingResult(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                searchData.value = user
            }, { error ->
                error.message?.let { Log.e("Error", it) }
            })
    }

    fun getFollowingResutl(): LiveData<List<ItemsGithubSearch>> {
        return searchData
    }

}