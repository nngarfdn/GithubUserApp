package com.iniudin.githubuserapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.iniudin.githubuserapp.ui.fragment.FollowersFragment
import com.iniudin.githubuserapp.ui.fragment.FollowingFragment

@Suppress("DEPRECATION")
class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String ? = null

        when(position){
            0 -> title = "Followers"
            1 -> title = "Following"
        }
        return  title
    }

}