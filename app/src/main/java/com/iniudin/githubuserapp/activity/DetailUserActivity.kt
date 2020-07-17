package com.iniudin.githubuserapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.model.User
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        changeView()

    }

    private fun changeView() {
        val user = intent.getParcelableExtra(EXTRA_USER) as User
        detailCompany.text = user.company
        detailFollowers.text = user.follower.toString()
        detailFollowing.text = user.following.toString()
        detailLocation.text = user.location
        detailRepo.text = user.repository.toString()
        detailUsername.text = user.username
        detailName.text = user.name
        val detailPhotoBackground = detailPhotoBackground
        Glide.with(this)
            .load(this.resources.getIdentifier(user.avatar, "drawable", this.packageName))
            .apply(bitmapTransform(GrayscaleTransformation()))
            .centerCrop()
            .into(detailPhotoBackground)
    }
}