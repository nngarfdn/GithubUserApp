package com.iniudin.githubuserapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iniudin.githubuserapp.network.GithubClient
import com.iniudin.githubuserapp.pojo.ItemsGithubSearch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers



class FollowersViewModel : ViewModel() {

    private val searchData: MutableLiveData<List<ItemsGithubSearch>> = MutableLiveData()
    fun setFollowers(username: String) {
        val service = GithubClient.initRetrofit()

        service.getFollowersResult(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                searchData.value = user
            }, { error ->
                error.message?.let { Log.e("Error", it) }
            })
    }

    fun getFollowersResutl(): LiveData<List<ItemsGithubSearch>> {
        return searchData
    }

}