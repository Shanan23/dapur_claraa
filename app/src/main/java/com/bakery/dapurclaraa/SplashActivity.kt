package com.bakery.dapurclaraa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var tvSplashVersion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tvSplashVersion = findViewById(R.id.tvSplashVersion)


        // Create an Intent to start the target activity
        val targetActivityIntent = Intent(this, LoginActivity::class.java)

        // Create a Handler to post a delayed intent
        val handler = Handler(Looper.getMainLooper())

        // Delay in milliseconds
        val delayMillis = 3000L // 2 seconds

        handler.postDelayed({
            // Post the intent after the specified delay
            startActivity(targetActivityIntent)
        }, delayMillis)
    }
}