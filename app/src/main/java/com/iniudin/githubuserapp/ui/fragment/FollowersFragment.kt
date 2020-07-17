package com.iniudin.githubuserapp.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.FollowersAdapter
import com.iniudin.githubuserapp.ui.activity.DetailUserActivity
import com.iniudin.githubuserapp.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

@Suppress("SameParameterValue")
class FollowersFragment : Fragment() {
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var adapter: FollowersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onResume() {
        super.onResume()
        getResultData()
        rvFollowers.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_followers, container, false)
        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        val user = activity?.intent?.getStringExtra(DetailUserActivity.EXTRA_USER)
        followersViewModel.setFollowers(user!!)
        getResultData()
        return view
    }

    private fun getResultData() {
        followersViewModel.getFollowersResutl().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter = FollowersAdapter(it)
                rvFollowers.adapter = adapter
                initRecView()
                isDataShow(true)
                isLoading(false)
            } else {
                isDataShow(false)
                isLoading(false)
                adapter = FollowersAdapter(it)
                rvFollowers.adapter = adapter
                initRecView()
            }
        })
    }

    private fun initRecView() {
        rvFollowers.layoutManager = LinearLayoutManager(context)
        rvFollowers.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }

    private fun isDataShow(status : Boolean) {
        if (status) txtNoDataFollower.visibility = View.INVISIBLE else txtNoDataFollower.visibility = View.VISIBLE
    }

    private fun isLoading(status : Boolean) {
        if (status) progressBarFollower.visibility = View.VISIBLE else progressBarFollower.visibility = View.INVISIBLE
    }


}