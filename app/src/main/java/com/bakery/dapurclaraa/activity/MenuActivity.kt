package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper

class MenuActivity : AppCompatActivity() {
    private lateinit var toSplashActivity: Intent
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private var backPressedTime: Long = 0
    private val threshold = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        toSplashActivity = Intent(this, SplashActivity::class.java)
        toSplashActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        toSplashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        toSplashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val cvMenuCakeType: CardView = findViewById(R.id.cvMenuCakeType)
        val cvMenuProfile: CardView = findViewById(R.id.cvMenuProfile)
        val cvMenuTransaction: CardView = findViewById(R.id.cvMenuTransaction)
        val cvMenuCustomer: CardView = findViewById(R.id.cvMenuCustomer)
        val cvBtnLogout: CardView = findViewById(R.id.cvBtnLogout)

        if (sharedPreferencesHelper.isAdmin) {
            cvMenuCakeType.visibility = View.GONE
            cvMenuProfile.visibility = View.GONE
            cvMenuTransaction.visibility = View.VISIBLE
            cvMenuCustomer.visibility = View.VISIBLE
        } else {
            cvMenuCakeType.visibility = View.VISIBLE
            cvMenuProfile.visibility = View.VISIBLE
            cvMenuTransaction.visibility = View.GONE
            cvMenuCustomer.visibility = View.GONE
        }

        val toListTypeActivity = Intent(this, ListTypeActivity::class.java)
        val toProfileActivity = Intent(this, ProfileActivity::class.java)
        val toTransactionActivity = Intent(this, ListPemesananActivity::class.java)
        val toCustomerActivity = Intent(this, ListCustomerActivity::class.java)


        cvMenuCakeType.setOnClickListener {
            startActivity(toListTypeActivity)
        }

        cvMenuProfile.setOnClickListener {
            startActivity(toProfileActivity)
        }

        cvMenuTransaction.setOnClickListener {
            startActivity(toTransactionActivity)
        }

        cvMenuCustomer.setOnClickListener {
            startActivity(toCustomerActivity)
        }

        cvBtnLogout.setOnClickListener {
            sharedPreferencesHelper.isAdmin = false
            sharedPreferencesHelper.isLoggedIn = false
            sharedPreferencesHelper.username = ""

            startActivity(toSplashActivity)
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < threshold) {
            // If the time difference is within the threshold, exit the app
            super.onBackPressed()
            finish()
        } else {
            // Display a toast indicating to press back again to exit
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}