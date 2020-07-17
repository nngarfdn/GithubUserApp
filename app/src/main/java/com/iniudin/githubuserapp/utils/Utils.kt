package com.iniudin.githubuserapp.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.iniudin.githubuserapp.model.User
import org.json.JSONObject
import java.io.IOException

class Utils {

    companion object {
        private fun getJsonDataFromAsset(context: Context): String? {
            val jsonString: String

            try {
                jsonString = context.assets.open("githubuser.json").bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getValueFromJson(context: Context): MutableList<User> {
            val daftarUser: MutableList<User> = ArrayList()
            val jsonFileString = getJsonDataFromAsset(context)
            Log.i("data ini adalah json", jsonFileString!!)
            Gson()
            val jsonObj = JSONObject(
                jsonFileString.substring(
                    jsonFileString.indexOf("{"),
                    jsonFileString.lastIndexOf("}") + 1
                )
            )
            val userJson = jsonObj.getJSONArray("users")
            for (i in 0 until userJson.length()) {
                val name = userJson.getJSONObject(i).getString("name")
                val username = userJson.getJSONObject(i).getString("username")
                val avatar = userJson.getJSONObject(i).getString("avatar")
                val company = userJson.getJSONObject(i).getString("company")
                val location = userJson.getJSONObject(i).getString("location")
                val repository = userJson.getJSONObject(i).getInt("repository")
                val follower = userJson.getJSONObject(i).getInt("follower")
                val following = userJson.getJSONObject(i).getInt("following")
                daftarUser.add(
                    User(
                        username,
                        name,
                        avatar,
                        company,
                        location,
                        repository,
                        follower,
                        following
                    )
                )
            }

            return daftarUser
        }

    }
}