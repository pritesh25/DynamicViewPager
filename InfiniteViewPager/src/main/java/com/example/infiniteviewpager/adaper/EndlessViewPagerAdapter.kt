package com.example.infiniteviewpager.adaper

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infiniteviewpager.R
import com.example.infiniteviewpager.model.CardInfo
import com.example.infiniteviewpager.model.CardStatus
import com.example.infiniteviewpager.util.BitmapUtil

class EndlessViewPagerAdapter(
        resources: Resources,
        private val data: List<CardInfo>
) : RecyclerView.Adapter<EndlessViewPagerAdapter.ViewHolder>() {

    private val frozenCardDrawable = BitmapUtil.createMaskedDrawable(
            resources,
            R.drawable.ic_card_item_image,
            R.mipmap.card_frozen_overlay
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val last4Digits: TextView = view.findViewById(R.id.last4Digits)
        private val cardImage: ImageView = view.findViewById(R.id.cardImage)

        fun bind(cardInfo: CardInfo) {
            last4Digits.text =
                    itemView.context.getString(R.string.card_number_format, cardInfo.last4Digits)

            when (cardInfo.status) {
                CardStatus.ACTIVE -> cardImage.setImageResource(R.drawable.ic_card_item_image)
                CardStatus.FROZEN -> cardImage.setImageDrawable(frozenCardDrawable)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card_item, parent, false))
    }
}