package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.DapurClaraaDB
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.helper.AlertDialogHelper
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.helper.Utils
import com.bakery.dapurclaraa.viewmodels.CustomerViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var admin: Admin
    private lateinit var alertDialogHelper: AlertDialogHelper
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var dbConnection: DapurClaraaDB
    private lateinit var tieRegisterUsername: TextInputEditText
    private lateinit var tieRegisterPassword: TextInputEditText
    private lateinit var tieRegisterAddress: TextInputEditText
    private lateinit var tieRegisterName: TextInputEditText
    private lateinit var tieRegisterPhone: TextInputEditText
    private lateinit var cvBtnRegister: CardView
    private lateinit var tvRegisterVersion: TextView
    private val customerViewModel: CustomerViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tieRegisterUsername = findViewById(R.id.tieRegisterUsername)
        tieRegisterPassword = findViewById(R.id.tieRegisterPassword)
        tieRegisterAddress = findViewById(R.id.tieRegisterAddress)
        tieRegisterName = findViewById(R.id.tieRegisterName)
        tieRegisterPhone = findViewById(R.id.tieRegisterPhone)
        cvBtnRegister = findViewById(R.id.cvBtnRegister)
        tvRegisterVersion = findViewById(R.id.tvRegisterVersion)
        val version = Utils().getVersionName(this)
        tvRegisterVersion.text = getString(R.string.version, version)

        dbConnection = DapurClaraaDB.getDatabase(applicationContext)

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        alertDialogHelper = AlertDialogHelper(this)
        val toMenuActivity = Intent(this, MenuActivity::class.java)

        cvBtnRegister.setOnClickListener(View.OnClickListener {
            val valUser = tieRegisterUsername.text
            val valPass = tieRegisterPassword.text
            val valName = tieRegisterName.text
            val valAddress = tieRegisterAddress.text
            val valPhone = tieRegisterPhone.text
            if (valName.isNullOrEmpty()) {
                tieRegisterName.error = "Nama tidak boleh kosong"
                return@OnClickListener
            }
            if (valUser.isNullOrEmpty()) {
                tieRegisterUsername.error = "Username tidak boleh kosong"
                return@OnClickListener
            }
            if (valPass.isNullOrEmpty()) {
                tieRegisterPassword.error = "Password tidak boleh kosong"
                return@OnClickListener
            }
            if (valAddress.isNullOrEmpty()) {
                tieRegisterAddress.error = "Alamat tidak boleh kosong"
                return@OnClickListener
            }
            if (valPhone.isNullOrEmpty()) {
                tieRegisterPhone.error = "Telepon tidak boleh kosong"
                return@OnClickListener
            }

            val customers = Customers(
                customerUsername = valUser.toString(),
                customerName = valName.toString(),
                customerPassword = valPass.toString(),
                customerAddress = valAddress.toString(),
                customerPhone = valPhone.toString()
            )

            customerViewModel.insertCustomer(
                customers
            )

            startActivity(toMenuActivity)
        })
    }
}