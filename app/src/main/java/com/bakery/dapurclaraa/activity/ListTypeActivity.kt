package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.adapter.ListTypeAdapter
import com.bakery.dapurclaraa.adapter.TypeItemClickListener
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.viewmodels.KueViewModel

class ListTypeActivity : AppCompatActivity(), TypeItemClickListener {

    private lateinit var toListKueActivity: Intent
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var listTypeAdapter: ListTypeAdapter
    private val kueViewModel: KueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_type)

        listTypeAdapter = ListTypeAdapter(this)
        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        toListKueActivity = Intent(this, ListKueActivity::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.rvCakeType)
        val cvBtnBack: CardView = findViewById(R.id.cvBtnBack)
        cvBtnBack.setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listTypeAdapter

        kueViewModel.type.observe(this) {
            listTypeAdapter.submitList(it)
            listTypeAdapter.notifyDataSetChanged()
        }

        kueViewModel.loadAllType()
    }

    override fun onTypeItemClicked(selectedItem: String) {
        toListKueActivity.putExtra("cakeType", selectedItem)
        startActivity(toListKueActivity)
    }
}