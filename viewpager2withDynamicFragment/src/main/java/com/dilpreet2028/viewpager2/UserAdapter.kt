package com.dilpreet2028.viewpager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserAdapter(private val userList: ArrayList<String>, mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun createFragment(position: Int): Fragment {
        return UserFragment.getInstance(userList[position])
    }

}
