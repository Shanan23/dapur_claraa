package com.bakery.dapurclaraa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Pembayaran

class ListPemesananAdapter(private val context: Context) :
    ListAdapter<Pembayaran, DaftarPembayaranViewHolder>(object :
        DiffUtil.ItemCallback<Pembayaran>() {

        override fun areItemsTheSame(oldItem: Pembayaran, newItem: Pembayaran): Boolean =
            oldItem.transactionId == newItem.transactionId

        override fun areContentsTheSame(oldItem: Pembayaran, newItem: Pembayaran): Boolean =
            oldItem.transactionDate == newItem.transactionDate
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarPembayaranViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pembayaran, parent, false)
        return DaftarPembayaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaftarPembayaranViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvPembayaranName.text = item.customerDetail.customerName.toString()
        holder.tvPembayaranAddress.text = context.getString(
            R.string.detail_transaction,
            item.transactionQuantity.toString(),
            item.kueDetail.cakeName
        )
    }
}

class DaftarPembayaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvPembayaranName: TextView = itemView.findViewById(R.id.tvPembayaranName)
    val tvPembayaranAddress: TextView = itemView.findViewById(R.id.tvPembayaranAddress)
}