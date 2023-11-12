package com.bakery.dapurclaraa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R

class ListTypeAdapter(private val itemClickListener: TypeItemClickListener) :
    ListAdapter<String, DaftarTypeViewHolder>(object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }) {

    private var selectedItem: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        return DaftarTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaftarTypeViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.isSelected = (item == selectedItem)

        holder.itemView.setOnClickListener {
            selectedItem = item
            notifyDataSetChanged()
            itemClickListener.onTypeItemClicked(item)
        }
        holder.tvType.text = item
    }

    fun getSelectedItem(): String? = selectedItem

}

class DaftarTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvType: TextView = itemView.findViewById(R.id.tvType)
}

interface TypeItemClickListener {
    fun onTypeItemClicked(selectedItem: String)
}