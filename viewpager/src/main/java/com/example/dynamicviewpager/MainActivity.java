package com.example.dynamicviewpager;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productAdvertise();
        setTimer();
    }

    private void productAdvertise() {

        final int[] currentPage = {0};
        final ArrayList<String> list = new ArrayList<>();

        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getApplicationContext(), list);
        final ViewPagerCustomDuration pager = findViewById(R.id.view_pager);
        pager.setScrollDurationFactor(5);
        pager.setAdapter(pagerAdapter);

        for (int i = 0; i < list.size(); i++) {
            pagerAdapter.addView(getLayoutInflater().inflate(R.layout.item_advertise, null), i);
        }

        pagerAdapter.notifyDataSetChanged();

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage[0] == list.size()) {
                    currentPage[0] = 0;
                }
                pager.setCurrentItem(currentPage[0]++, true);
            }
        };
        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 1800);
    }

    private void setTimer() {

    }
}