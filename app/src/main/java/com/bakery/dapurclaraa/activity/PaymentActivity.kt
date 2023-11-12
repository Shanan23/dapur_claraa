package com.bakery.dapurclaraa.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.database.objects.Pembayaran
import com.bakery.dapurclaraa.helper.AlertDialogHelper
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.viewmodels.TransactionViewModel
import com.google.gson.Gson
import java.io.ByteArrayOutputStream

class PaymentActivity : AppCompatActivity() {

    private lateinit var alertDialogHelper: AlertDialogHelper
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private var payObj: Pembayaran = Pembayaran(image = null)
    private var payJson: String = ""

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImage: Bitmap? = null

    private val tvCustDetail: TextView by lazy { findViewById(R.id.tvCustDetail) }
    private val tvCustAddress: TextView by lazy { findViewById(R.id.tvCustAddress) }
    private val tvLblDeliveryCost: TextView by lazy { findViewById(R.id.tvLblDeliveryCost) }
    private val tvPayTotal: TextView by lazy { findViewById(R.id.tvPayTotal) }
    private val ivGallery: ImageView by lazy { findViewById(R.id.ivGallery) }
    private val tvLblImageUploaded: TextView by lazy { findViewById(R.id.tvLblImageUploaded) }
    private val cvBtnBack: CardView by lazy { findViewById(R.id.cvBtnBack) }
    private val cvBtnOrder: CardView by lazy { findViewById(R.id.cvBtnOrder) }
    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        val intent = intent  // If you are inside an Activity, you can directly use `intent`
        val extraValue: String? = intent.getStringExtra("payObj")

        if (extraValue != null) {
            payJson = extraValue
        }
        if (payJson != "") payObj = Gson().fromJson(payJson, Pembayaran::class.java)
        val toMenuActivity = Intent(this, MenuActivity::class.java)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        var customers = Gson().fromJson(sharedPreferencesHelper.userObj, Customers::class.java)

        if (payObj != null) {
            tvCustDetail.text = getString(
                R.string.cust_name_delivery, customers.customerName, customers.customerPhone
            )

            var totalPay = payObj.transactionTotal + payObj.transactionDeliveryCost.toInt()

            tvCustAddress.text = customers.customerAddress
            tvLblDeliveryCost.text = getString(R.string.rp, payObj.transactionDeliveryCost)
            tvPayTotal.text = getString(R.string.total_pembayaran_rp, totalPay.toString())
        }

        ivGallery.setOnClickListener {
            pickImageFromGallery()
        }
        alertDialogHelper = AlertDialogHelper(this)


        cvBtnOrder.setOnClickListener {
            if (selectedImage != null) {
                // Convert Bitmap to ByteArray
                val imageByteArray = bitmapToByteArray(selectedImage!!)

                payObj.image = imageByteArray
                transactionViewModel.insertPembayaran(payObj)

                startActivity(toMenuActivity)
            } else {
                alertDialogHelper.showAlertDialog(
                    "Konfirmasi gagal",
                    "Silakan upload bukti pembayaran"
                )
            }
        }

        cvBtnBack.setOnClickListener { finish() }
    }

    private fun pickImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Get the selected image URI
            val selectedImageUri = data.data

            // Convert URI to Bitmap
            selectedImage = BitmapFactory.decodeStream(
                contentResolver.openInputStream(selectedImageUri!!)
            )

            tvLblImageUploaded.text = "*Upload Berhasil*"
            tvLblImageUploaded.setTextColor(Color.GREEN)
        }
    }

    // Convert Bitmap to ByteArray
    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}