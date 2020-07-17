package com.iniudin.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.FollowingAdapter
import com.iniudin.githubuserapp.ui.activity.DetailUserActivity
import com.iniudin.githubuserapp.viewmodel.FollowwingViewModel
import kotlinx.android.synthetic.main.fragment_following.*


@Suppress("SameParameterValue")
class FollowingFragment : Fragment() {
    private lateinit var followingViewModel: FollowwingViewModel
    private lateinit var adapter: FollowingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onResume() {
        super.onResume()
        getResultData()
        rvFollowing.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_following, container, false)
        followingViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowwingViewModel::class.java)

        val user = activity?.intent?.getStringExtra(DetailUserActivity.EXTRA_USER)
        followingViewModel.setFollowing(user!!)
        getResultData()
        return view
    }

    private fun getResultData() {
        followingViewModel.getFollowingResutl().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter = FollowingAdapter(it)
                rvFollowing.adapter = adapter
                initRecView()
                isDataShow(true)
                isLoading(false)
            } else {
                isDataShow(false)
                isLoading(false)
                adapter = FollowingAdapter(it)
                rvFollowing.adapter = adapter
                initRecView()
            }
        })
    }

    private fun initRecView() {
        rvFollowing.layoutManager = LinearLayoutManager(context)
        rvFollowing.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }

    private fun isDataShow(status: Boolean) {
        if (status) txtNoDataFollowing.visibility =
            View.INVISIBLE else txtNoDataFollowing.visibility = View.VISIBLE
    }

    private fun isLoading(status: Boolean) {
        if (status) progressBarFollowing.visibility =
            View.VISIBLE else progressBarFollowing.visibility = View.INVISIBLE
    }
}