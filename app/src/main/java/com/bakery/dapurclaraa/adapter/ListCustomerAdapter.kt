package com.bakery.dapurclaraa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Customers

class ListCustomerAdapter :
    ListAdapter<Customers, DaftarCustomerViewHolder>(object : DiffUtil.ItemCallback<Customers>() {

        override fun areItemsTheSame(oldItem: Customers, newItem: Customers): Boolean =
            oldItem.customerId == newItem.customerId

        override fun areContentsTheSame(oldItem: Customers, newItem: Customers): Boolean =
            oldItem.customerUsername == newItem.customerUsername
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarCustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return DaftarCustomerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaftarCustomerViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvCustomerName.text = item.customerUsername
        holder.tvCustomerAddress.text = item.customerAddress
    }
}

class DaftarCustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvCustomerName: TextView = itemView.findViewById(R.id.tvCustomerName)
    val tvCustomerAddress: TextView = itemView.findViewById(R.id.tvCustomerAddress)
}