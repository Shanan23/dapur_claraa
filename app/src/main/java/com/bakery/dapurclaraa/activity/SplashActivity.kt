package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.UtteranceProgressListener
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.helper.Utils
import com.bakery.dapurclaraa.viewmodels.AdminViewModel
import com.bakery.dapurclaraa.viewmodels.KueViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var tvSplashVersion: TextView
    private val kueViewModel: KueViewModel by viewModels()
    private val adminViewModel: AdminViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tvSplashVersion = findViewById(R.id.tvSplashVersion)
        val version = Utils().getVersionName(this)
        tvSplashVersion.text = getString(R.string.version, version)

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        val toMenuActivity = Intent(this, MenuActivity::class.java)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Create an Intent to start the target activity
        val targetActivityIntent = Intent(this, LoginActivity::class.java)

        // Create a Handler to post a delayed intent
        val handler = Handler(Looper.getMainLooper())

        // Delay in milliseconds
        val delayMillis = 3000L

        var dwImage = Utils().getDrawable(this, R.drawable.cake_image)
        var dwToByteArray = Utils().drawableToByteArray(dwImage)

        handler.postDelayed({
            // Post the intent after the specified delay
            if (sharedPreferencesHelper.isLoggedIn) {
                startActivity(toMenuActivity)
            } else {

                var admin = Admin()
                admin.adminName = "admin"
                admin.adminPass = "12345"

                adminViewModel.insertAdmin(admin)

                val listKue = mutableListOf<Kue>()
                for (i in 1..20) {
                    var kue: Kue
                    if (i < 6) {
                        kue = Kue(
                            cakeId = i,
                            cakeType = "Klapertart",
                            cakeName = "Klapertart $i",
                            cakePrice = "5000",
                            cakeStock = "$i",
                            // ... other properties with default or specific values
                            image = dwToByteArray,  // assuming the image property is a ByteArray
                            cakeDeliveryCost = i * 1000,  // example calculation for delivery cost
                            cakeSize = "Size $i"
                        )
                    } else if (i in 6..10) {
                        kue = Kue(
                            cakeId = i,
                            cakeType = "Cake",
                            cakeName = "Cake $i",
                            cakePrice = "6000",
                            cakeStock = "$i",
                            // ... other properties with default or specific values
                            image = dwToByteArray,  // assuming the image property is a ByteArray
                            cakeDeliveryCost = i * 1000,  // example calculation for delivery cost
                            cakeSize = "Size $i"
                        )
                    } else if (i in 11..15) {
                        kue = Kue(
                            cakeId = i,
                            cakeType = "Marmer",
                            cakeName = "Marmer $i",
                            cakePrice = "7000",
                            cakeStock = "$i",
                            // ... other properties with default or specific values
                            image = dwToByteArray,  // assuming the image property is a ByteArray
                            cakeDeliveryCost = i * 1000,  // example calculation for delivery cost
                            cakeSize = "Size $i"
                        )
                    } else {
                        kue = Kue(
                            cakeId = i,
                            cakeType = "Puding",
                            cakeName = "Puding $i",
                            cakePrice = "8000",
                            cakeStock = "$i",
                            // ... other properties with default or specific values
                            image = dwToByteArray,  // assuming the image property is a ByteArray
                            cakeDeliveryCost = i * 1000,  // example calculation for delivery cost
                            cakeSize = "Size $i"
                        )
                    }
                    listKue.add(kue)

                }

                kueViewModel.loadAllKue()

                kueViewModel.kue.observe(this) {
                    kueViewModel.deleteAll(it)

                    kueViewModel.insertKue(listKue)
                }

                startActivity(targetActivityIntent)
            }
        }, delayMillis)
    }
}