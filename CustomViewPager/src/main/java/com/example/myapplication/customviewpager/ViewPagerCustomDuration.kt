package com.example.myapplication.customviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Interpolator
import androidx.viewpager.widget.ViewPager


class ViewPagerCustomDuration : ViewPager {
    constructor(context: Context) : super(context) {
        postInitViewPager()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        postInitViewPager()
    }

    private var mScroller: ScrollerCustomDuration? = null

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private fun postInitViewPager() {
        try {
            val scroller = ViewPager::class.java.getDeclaredField("mScroller")
            scroller.isAccessible = true
            val interpolator = ViewPager::class.java.getDeclaredField("sInterpolator")
            interpolator.isAccessible = true
            mScroller = ScrollerCustomDuration(context,
                    interpolator.get(null) as Interpolator)
            scroller.set(this, mScroller)
        } catch (e: Exception) {
        }
    }

    /**
     * Set the factor by which the duration will change
     */
    fun setScrollDurationFactor(scrollFactor: Double) {
        mScroller!!.setScrollDurationFactor(scrollFactor)
    }

    /**********************************************************************/
    /*      for infinite scroll */
    /**********************************************************************/
    /*override fun setAdapter(adapter: PagerAdapter?): Unit {
        super.setAdapter(adapter)
        // offset first element so that we can scroll to the left
        currentItem = 0
    }

    override fun setCurrentItem(item: Int) {
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        var item = item
        if (adapter!!.count == 0) {
            super.setCurrentItem(item, smoothScroll)
            return
        }
        item = getOffsetAmount() + item % adapter!!.count
        super.setCurrentItem(item, smoothScroll)
    }

    override fun getCurrentItem(): Int {
        if (adapter!!.count == 0) {
            return super.getCurrentItem()
        }
        val position = super.getCurrentItem()
        return if (adapter is InfinitePagerAdapter) {
            val infAdapter: InfinitePagerAdapter? = adapter as InfinitePagerAdapter?
            // Return the actual item position in the data backing InfinitePagerAdapter
            position % infAdapter!!.getRealCount()
        } else {
            super.getCurrentItem()
        }
    }

    private fun getOffsetAmount(): Int {
        if (adapter!!.count == 0) {
            return 0
        }
        return if (adapter is InfinitePagerAdapter) {
            val infAdapter: InfinitePagerAdapter? = adapter as InfinitePagerAdapter?
            // allow for 100 back cycles from the beginning
            // should be enough to create an illusion of infinity
            // warning: scrolling to very high values (1,000,000+) results in
            // strange drawing behaviour
            infAdapter!!.getRealCount() * 100
        } else {
            0
        }
    }*/
}