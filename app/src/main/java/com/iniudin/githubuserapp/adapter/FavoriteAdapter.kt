package com.iniudin.githubuserapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.pojo.GithubProfile
import com.iniudin.githubuserapp.ui.activity.DetailUserActivity
import kotlinx.android.synthetic.main.user_item.view.*

class FavoriteAdapter (private val list: List<GithubProfile>) : RecyclerView.Adapter<FavoriteAdapter.FollowersViewHolder>() {
    inner class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder = FollowersViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].avatar_url)
            .into(holder.itemView.imgPhoto)
        holder.itemView.txtName.text = list[position].login
        holder.itemView.txtLocation.text = list[position].id.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER,list[position].login)
            holder.itemView.context.startActivity(intent)
        }
    }
}