package com.bakery.dapurclaraa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Kue

class ListKueAdapter :
    ListAdapter<Kue, DaftarKueViewHolder>(object : DiffUtil.ItemCallback<Kue>() {

        override fun areItemsTheSame(oldItem: Kue, newItem: Kue): Boolean =
            oldItem.cakeId == newItem.cakeId

        override fun areContentsTheSame(oldItem: Kue, newItem: Kue): Boolean =
            oldItem.cakeStock == newItem.cakeStock
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarKueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kue, parent, false)
        return DaftarKueViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaftarKueViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvCakeName.text = item.cakeName
        holder.tvCakePrice.text = item.cakePrice
    }
}

class DaftarKueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvCakeName: TextView = itemView.findViewById(R.id.tvCakeName)
    val tvCakePrice: TextView = itemView.findViewById(R.id.tvCakePrice)
}