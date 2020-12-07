package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BannerFragment : Fragment() {

    private var mUserView: TextView? = null

    companion object {
        fun getInstance(name: String): Fragment {
            val fragment = BannerFragment()
            val arg = Bundle()
            arg.putString("name", name)
            fragment.arguments = arg
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserView = view.findViewById(R.id.user_name)
        val name = arguments?.getString("name")
        mUserView?.text = "Hello $name"
    }
}