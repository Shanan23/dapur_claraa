package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {
    private val TAG: String = "RegisterActivity"
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
        val handler = Handler(Looper.getMainLooper())

        val delayMillis = 2000L

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        alertDialogHelper = AlertDialogHelper(this)
        val toMenuActivity = Intent(this, MenuActivity::class.java)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Observe LiveData changes
        customerViewModel.customer.observe(this) { customer ->
            if (customer.customerName.isNotEmpty()) {

                // Save data
                sharedPreferencesHelper.username = customer.customerName
                sharedPreferencesHelper.userObj = Gson().toJson(customer)
                sharedPreferencesHelper.isAdmin = false
                sharedPreferencesHelper.isLoggedIn = true

                Log.d(TAG, "Registrasi success ${customer.customerName}")

                startActivity(toMenuActivity)
            } /*else {
                alertDialogHelper.showAlertDialog("Registrasi gagal", "User tidak ditemukan")
            }*/
        }

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


            handler.postDelayed({
                customerViewModel.validateCustomer(valName.toString(), valPass.toString())
            }, delayMillis)
//            startActivity(toMenuActivity)
        })
    }
}