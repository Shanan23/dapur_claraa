package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.database.objects.Pembayaran
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.google.gson.Gson

class DetailKueActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var toPaymentActivity: Intent
    private lateinit var cakeJson: String
    var quantityOrder: Int = 1
    var pembayaran: Pembayaran = Pembayaran(image = null)
    private val tvCakeName: TextView by lazy { findViewById(R.id.tvCakeName) }
    private val ivCake: ImageView by lazy { findViewById(R.id.ivCake) }
    private val tvCakeType: TextView by lazy { findViewById(R.id.tvCakeType) }
    private val tvCakePrice: TextView by lazy { findViewById(R.id.tvCakePrice) }
    private val tvCakeSize: TextView by lazy { findViewById(R.id.tvCakeSize) }
    private val tvCakeOrder: TextView by lazy { findViewById(R.id.tvCakeOrder) }
    private val quantityPicker: NumberPicker by lazy { findViewById(R.id.quantityPicker) }
    private val cvBtnBack: CardView by lazy { findViewById(R.id.cvBtnBack) }
    private val cvBtnOrder: CardView by lazy { findViewById(R.id.cvBtnOrder) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kue)

        val intent = intent  // If you are inside an Activity, you can directly use `intent`
        val extraValue: String? = intent.getStringExtra("cakeObj")

        if (extraValue != null) {
            cakeJson = extraValue
        }

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        var cakeObj = Gson().fromJson(cakeJson, Kue::class.java)
        var customers = Gson().fromJson(sharedPreferencesHelper.userObj, Customers::class.java)

        tvCakeName.text = cakeObj.cakeName
        tvCakeType.text = getString(R.string.jenis, cakeObj.cakeType)
        tvCakePrice.text = getString(R.string.harga, cakeObj.cakePrice)
        tvCakeSize.text = getString(R.string.ukuran, cakeObj.cakeSize)

        quantityPicker.maxValue = 3
        quantityPicker.minValue = 1

        quantityPicker.setOnValueChangedListener { _, _, newVal ->
            quantityOrder = newVal
        }

        cvBtnBack.setOnClickListener {
            finish()
        }


        toPaymentActivity = Intent(this, PaymentActivity::class.java)

        cvBtnOrder.setOnClickListener {
            pembayaran.customerId = customers.customerId
            pembayaran.cakeId = cakeObj.cakeId
            pembayaran.transactionAddress = customers.customerAddress
            pembayaran.transactionPhone = customers.customerPhone
            pembayaran.transactionQuantity = quantityOrder
            pembayaran.transactionDeliveryCost = cakeObj.cakeDeliveryCost.toString()

            toPaymentActivity.putExtra("payObj", Gson().toJson(pembayaran))
            startActivity(toPaymentActivity)
        }
    }
}