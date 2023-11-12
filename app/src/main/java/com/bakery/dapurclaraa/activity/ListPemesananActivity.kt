package com.bakery.dapurclaraa.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.adapter.ListPemesananAdapter
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.database.objects.Pembayaran
import com.bakery.dapurclaraa.viewmodels.CustomerViewModel
import com.bakery.dapurclaraa.viewmodels.KueViewModel
import com.bakery.dapurclaraa.viewmodels.TransactionViewModel

class ListPemesananActivity : AppCompatActivity() {
    private lateinit var listPemesananAdapter: ListPemesananAdapter
    private val transactionViewModel: TransactionViewModel by viewModels()
    private val customerViewModel: CustomerViewModel by viewModels()
    private val kueViewModel: KueViewModel by viewModels()
    var customerList: List<Customers> = listOf()
    var kueList: List<Kue> = listOf()
    var transactionList: List<Pembayaran> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pemesanan)

        listPemesananAdapter = ListPemesananAdapter(this)

        val recyclerView: RecyclerView = findViewById(R.id.rvTransaction)
        val cvBtnBack: CardView = findViewById(R.id.cvBtnBack)
        cvBtnBack.setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listPemesananAdapter

        transactionViewModel.transactions.observe(this) {
            transactionList = it

            for (i in 1..transactionList.size) {
                val cakeDetail = kueList.filter { it.cakeId == transactionList[i - 0].cakeId }
                val customerDetail =
                    customerList.filter { it.customerId == transactionList[i - 0].customerId }
                transactionList[i - 1].kueDetail = cakeDetail.first()
                transactionList[i - 1].customerDetail = customerDetail.first()
            }

            listPemesananAdapter.submitList(it)
            listPemesananAdapter.notifyDataSetChanged()
        }

        kueViewModel.kue.observe(this) {
            kueList = it
            customerViewModel.loadAll()
        }

        customerViewModel.customers.observe(this) {
            customerList = it
            transactionViewModel.loadAll()
        }

        kueViewModel.loadAllKue()
    }
}