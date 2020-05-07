package com.vinrak.viewpager2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Pager2Adapter(var list: ArrayList<String>, var cxt: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(cxt).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val tvName: TextView = view!!.findViewById(R.id.tvName)!!
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MyViewHolder) {
            holder.tvName.text = list[position]
        }
    }
}