package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val ivInstagram: ImageView = findViewById(R.id.ivInstagram)
        val ivWhatsapp: ImageView = findViewById(R.id.ivWhatsapp)
        val ivTokopedia: ImageView = findViewById(R.id.ivTokopedia)
        val cvBtnBack: CardView = findViewById(R.id.cvBtnBack)

        val urlInstagram =
            "https://www.instagram.com/dapurclaraa/?utm_source=ig_web_button_share_sheet&igshid=OGQ5ZDc2ODk2ZA=="
        val urlWhatsapp = "https://wa.me/message/D6J5HXPLMSRPD1"
        val urlTokopedia = "https://tokopedia.link/dapurclara"

        val intentInstagram = Intent(Intent.ACTION_VIEW, Uri.parse(urlInstagram))
        val intentWhatsapp = Intent(Intent.ACTION_VIEW, Uri.parse(urlWhatsapp))
        val intentTokopedia = Intent(Intent.ACTION_VIEW, Uri.parse(urlTokopedia))

        ivInstagram.setOnClickListener {
            if (intentInstagram.resolveActivity(packageManager) != null) {
                startActivity(intentInstagram)
            }
        }
        ivWhatsapp.setOnClickListener {
            if (intentWhatsapp.resolveActivity(packageManager) != null) {
                startActivity(intentWhatsapp)
            }
        }
        ivTokopedia.setOnClickListener {
            if (intentTokopedia.resolveActivity(packageManager) != null) {
                startActivity(intentTokopedia)
            }
        }
        cvBtnBack.setOnClickListener {
            finish()
        }
    }
}