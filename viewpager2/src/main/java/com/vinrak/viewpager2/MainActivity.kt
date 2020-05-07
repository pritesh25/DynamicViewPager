package com.vinrak.viewpager2

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentPage = intArrayOf(0)

        val viewpager2Test: ViewPager2 = findViewById(R.id.viewpager2Test)
        val list = arrayListOf("A", "B", "C")
        viewpager2Test.adapter = Pager2Adapter(list, applicationContext)

        val tabDots: TabLayout = findViewById(R.id.tabDots)

        TabLayoutMediator(tabDots, viewpager2Test, TabLayoutMediator.TabConfigurationStrategy { tab, position -> // Styling each tab here
            //tab.text = "Tab $position"
        }).attach()


        val handler = Handler()
        val update = Runnable {
            if (currentPage[0] == list.size) {
                currentPage[0] = 0
            }
            viewpager2Test.setCurrentItem(currentPage[0]++, true)
        }
        val timer = Timer() // This will create a new Thread
        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, 5000, 5000)

    }
}
