package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.adapter.KueItemClickListener
import com.bakery.dapurclaraa.adapter.ListKueAdapter
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.viewmodels.KueViewModel
import com.google.gson.Gson

class ListKueActivity : AppCompatActivity(), KueItemClickListener {

    private lateinit var toDetailKueActivity: Intent
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

        listKueAdapter = ListKueAdapter(this)
        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        toDetailKueActivity = Intent(this, DetailKueActivity::class.java)

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

    override fun onTypeItemClicked(selectedItem: Kue) {
        toDetailKueActivity.putExtra("cakeObj", Gson().toJson(selectedItem))
        startActivity(toDetailKueActivity)
    }
}