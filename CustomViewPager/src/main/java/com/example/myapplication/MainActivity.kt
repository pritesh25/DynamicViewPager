package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.customviewpager.ViewPagerCustomDuration
import com.google.android.material.tabs.TabLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    private val mTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productAdvertise()

    }

    private fun productAdvertise() {

        val adapter: PagerAdapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            var colours = getFragmentList()

            override fun getCount(): Int {
                Log.d(mTag, "size = ${colours.size}")
                return colours.size
            }

            override fun getItem(position: Int): Fragment {
                val fragment: Fragment = BannerFragment()
                val args = Bundle()
                args.putString("name", colours[position])
                //args.putInt("identifier", position)
                fragment.arguments = args
                return fragment
            }
        }

        val currentPage = intArrayOf(0)

        val pager = findViewById<ViewPagerCustomDuration>(R.id.view_pager)
        pager.setScrollDurationFactor(5.0)
        pager.adapter = adapter

        val tabLayout: TabLayout = findViewById<TabLayout>(R.id.tabDots)
        tabLayout.setupWithViewPager(pager, true)

        val updateHandler = Handler()
        val updateRunnable = Runnable {
            if (currentPage[0] == getFragmentList().size) {
                currentPage[0] = 0
            }
            pager.setCurrentItem(currentPage[0]++, true)
        }
        val timer = Timer() // This will create a new Thread
        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                updateHandler.post(updateRunnable)
            }
        }, 500, 1800)
    }

    private fun getFragmentList(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
        list.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png")
        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png")
        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp")
        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png")
        return list
    }

}