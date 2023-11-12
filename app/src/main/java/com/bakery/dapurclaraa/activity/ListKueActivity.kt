package com.bakery.dapurclaraa.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.adapter.ListKueAdapter
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.viewmodels.KueViewModel

class ListKueActivity : AppCompatActivity() {

    private var cakeType: String = ""
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var listKueAdapter: ListKueAdapter
    private val kueViewModel: KueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_kue)
        val intent = intent  // If you are inside an Activity, you can directly use `intent`
        val extraValue: String? = intent.getStringExtra("cakeType")

        if (extraValue != null) {
            cakeType = extraValue
        }

        listKueAdapter = ListKueAdapter()
        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)

        val recyclerView: RecyclerView = findViewById(R.id.rvCake)
        val cvBtnBack: CardView = findViewById(R.id.cvBtnBack)
        cvBtnBack.setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = listKueAdapter

        kueViewModel.kue.observe(this) {
            listKueAdapter.submitList(it)
            listKueAdapter.notifyDataSetChanged()
        }

        if (cakeType != "") kueViewModel.loadAllByType(cakeType)
    }
}