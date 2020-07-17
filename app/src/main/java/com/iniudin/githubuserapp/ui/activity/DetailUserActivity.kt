package com.iniudin.githubuserapp.ui.activity

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.ViewPagerAdapter
import com.iniudin.githubuserapp.database.DatabaseContract.CONTENT_URI
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.AVATAR
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.COMPANY
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERS
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERSURL
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.ID
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOCATION
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOGIN
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.NAME
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.PUBLICREPOS
import com.iniudin.githubuserapp.database.MappingHelper
import com.iniudin.githubuserapp.pojo.GithubProfile
import com.iniudin.githubuserapp.viewmodel.ProfileViewModel
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_detail_user.*

@Suppress("NAME_SHADOWING", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailUserActivity : AppCompatActivity() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var dataUser: GithubProfile
    private lateinit var dataFavorite: ArrayList<GithubProfile>
    private lateinit var uriWithId: Uri


    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        initViewModels()
        changeView()
        setupTabLayout()
        setupFavorite()

    }

    private fun setupFavorite() {
        profileViewModel.getProfileResutl().observe(this, Observer { users ->
            dataUser = users
            uriWithId = Uri.parse(CONTENT_URI.toString() + "/" + users?.id)
            val dataGitFav = contentResolver?.query(uriWithId, null, null, null, null)
            dataFavorite = MappingHelper.mapCursorToArrayList(dataGitFav)
            var isFavorite: Boolean
            isFavorite = dataFavorite.contains(users)
            setIconFavorite(isFavorite)
            fabFavorite.setOnClickListener {
                if (isFavorite) {
                    contentResolver.delete(uriWithId, null, null)
                    Toast.makeText(this, "Berhasil hapus favorite", Toast.LENGTH_SHORT).show()
                    isFavorite = false
                    setIconFavorite(isFavorite)
                } else {
                    val values = putValues()
                    contentResolver.insert(CONTENT_URI, values)
                    users?.login
                    Toast.makeText(this, "Berhasil menambahkan", Toast.LENGTH_SHORT).show()
                    isFavorite = true
                    setIconFavorite(isFavorite)
                }
            }

        })
    }

    private fun setIconFavorite(favorite: Boolean) {
        if (favorite){
            fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun putValues(): ContentValues {
        val values = ContentValues()
        values.put(ID, dataUser.id)
        values.put(AVATAR, dataUser.avatar_url)
        values.put(COMPANY, dataUser.company)
        values.put(FOLLOWERS, dataUser.followers)
        values.put(FOLLOWERSURL, dataUser.following)
        values.put(LOCATION, dataUser.location)
        values.put(LOGIN, dataUser.login)
        values.put(NAME, dataUser.name)
        values.put(PUBLICREPOS, dataUser.public_repos)
        return values
    }


    private fun initViewModels() {
        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ProfileViewModel::class.java)
    }

    private fun setupTabLayout() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(viewPager)
    }

    private fun changeView() {
        val user = intent.getStringExtra(EXTRA_USER).orEmpty()
        profileViewModel.setProfile(user)
        profileViewModel.getProfileResutl().observe(this, Observer { user ->
            detailCompany.text = user.company
            detailFollowers.text = user.followers.toString()
            detailFollowing.text = user.following.toString()
            detailLocation.text = user?.location
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