package com.iniudin.githubuserapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.ViewPagerAdapter
import com.iniudin.githubuserapp.viewmodel.ProfileViewModel
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_detail_user.*

@Suppress("NAME_SHADOWING", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailUserActivity : AppCompatActivity() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        changeView()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(viewPager)
    }

    private fun changeView() {
        val user = intent.getStringExtra(EXTRA_USER)
        profileViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ProfileViewModel::class.java)
        profileViewModel.setProfile(user)
        profileViewModel.getProfileResutl().observe(this, Observer { user ->
            detailCompany.text = user.company
            detailFollowers.text = user.followers.toString()
            detailFollowing.text = user.following.toString()
            detailLocation.text = user.location
            detailRepo.text = user.public_repos.toString()
            detailUsername.text = user.login
            detailName.text = user.name
            val detailPhotoBackground = detailPhotoBackground
            Glide.with(this)
                .load(user.avatar_url)
                .apply(bitmapTransform(GrayscaleTransformation()))
                .centerCrop()
                .into(detailPhotoBackground)
            progressDetail.visibility = View.INVISIBLE
        })

    }
}