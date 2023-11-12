package com.bakery.dapurclaraa.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.adapter.ListCustomerAdapter
import com.bakery.dapurclaraa.viewmodels.CustomerViewModel

class ListCustomerActivity : AppCompatActivity() {
    private lateinit var listCustomerAdapter: ListCustomerAdapter
    private val customerViewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_customer)

        listCustomerAdapter = ListCustomerAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.rvCustomer)
        val cvBtnBack: CardView = findViewById(R.id.cvBtnBack)
        cvBtnBack.setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listCustomerAdapter

        customerViewModel.customers.observe(this) {
            listCustomerAdapter.submitList(it)
            listCustomerAdapter.notifyDataSetChanged()
        }

        customerViewModel.loadAll()
    }
}