package com.iniudin.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iniudin.githubuserapp.network.GithubClient
import com.iniudin.githubuserapp.pojo.GithubSearch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class SearchViewModel : ViewModel() {

    private var searchData: MutableLiveData<GithubSearch> = MutableLiveData()

    fun setSearch(username: String) {
        val service = GithubClient.initRetrofit()

        service.getSearhResult(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                searchData.value = user
            }, { error ->
                error.message?.let { Log.e("Error", it) }
            })
    }

    fun getSearchResutl(): LiveData<GithubSearch> {
        return searchData
    }

}