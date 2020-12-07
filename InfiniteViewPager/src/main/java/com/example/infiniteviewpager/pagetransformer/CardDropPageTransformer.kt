package com.example.infiniteviewpager.pagetransformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.infiniteviewpager.R

class CardDropPageTransformer : ViewPager2.PageTransformer {
    private var offset = 0f

    override fun transformPage(page: View, position: Float) {
        if (offset == 0f)
            offset = page.context.resources.getDimension(R.dimen.card_drop_offset)

        page.translationY = if (position > 0f) -offset * position else offset * position
    }
}