package com.iniudin.githubuserapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.ui.activity.DetailUserActivity
import com.iniudin.githubuserapp.pojo.ItemsGithubSearch
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter( val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var cardList: List<ItemsGithubSearch>

    fun setData(cardList: List<ItemsGithubSearch>){
        this.cardList = cardList
    }

    inner class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.txtName!!
        val location = v.txtLocation!!
        val photo: ImageView = v.imgPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = cardList[position]

        holder.name.text = currentItem.login
        holder.location.text = currentItem.id.toString()

        Glide.with(context)
            .load(currentItem.avatar_url)
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER, currentItem.login)
            context.startActivity(intent)
        }
    }
}