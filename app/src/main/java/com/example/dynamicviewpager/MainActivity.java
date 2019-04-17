package com.example.dynamicviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager = null;
    private MainPagerAdapter pagerAdapter = null;
    ArrayList<String> list = new ArrayList<>();
    private int currentPage = 0;

    /**
     * https://homepages.cae.wisc.edu/~ece533/images/airplane.png
     * https://homepages.cae.wisc.edu/~ece533/images/arctichare.png
     * https://homepages.cae.wisc.edu/~ece533/images/baboon.png
     * https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp
     * https://homepages.cae.wisc.edu/~ece533/images/boat.png
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");

        pagerAdapter = new MainPagerAdapter(getApplicationContext(), list);
        pager = findViewById(R.id.view_pager);
        pager.setAdapter(pagerAdapter);

        for (int i = 0; i < list.size(); i++) {
            pagerAdapter.addView(getLayoutInflater().inflate(R.layout.item_advertise, null), i);
        }

        pagerAdapter.notifyDataSetChanged();

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        setTimer();
    }

    private void setTimer() {

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == list.size()) {
                    currentPage = 0;
                }
                pager.setCurrentItem(currentPage++, true);
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

    public void addView(View newPage) {
        int pageIndex = pagerAdapter.addView(newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem(pageIndex, true);
    }

    public void removeView(View defunctPage) {
        int pageIndex = pagerAdapter.removeView(pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem(pageIndex);
    }

    public View getCurrentPage() {
        return pagerAdapter.getView(pager.getCurrentItem());
    }

    public void setCurrentPage(View pageToShow) {
        pager.setCurrentItem(pagerAdapter.getItemPosition(pageToShow), true);
    }

}
