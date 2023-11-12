package com.bakery.dapurclaraa.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.R
import com.bakery.dapurclaraa.database.DapurClaraaDB
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.helper.AlertDialogHelper
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.helper.Utils
import com.bakery.dapurclaraa.viewmodels.AdminViewModel
import com.bakery.dapurclaraa.viewmodels.CustomerViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private val TAG: String = "LoginActivity"
    private lateinit var admin: Admin
    private lateinit var alertDialogHelper: AlertDialogHelper
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var dbConnection: DapurClaraaDB
    private lateinit var tieLoginUsername: TextInputEditText
    private lateinit var tieLoginPassword: TextInputEditText
    private lateinit var switchAdmin: SwitchCompat
    private lateinit var cvBtnLogin: CardView
    private lateinit var tvLoginVersion: TextView
    private lateinit var tvToRegister: TextView
    private val adminViewModel: AdminViewModel by viewModels()
    private val customerViewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        tieLoginUsername = findViewById(R.id.tieLoginUsername)
        tieLoginPassword = findViewById(R.id.tieLoginPassword)
        switchAdmin = findViewById(R.id.switchAdmin)
        cvBtnLogin = findViewById(R.id.cvBtnBack)
        tvLoginVersion = findViewById(R.id.tvLoginVersion)
        tvToRegister = findViewById(R.id.tvToRegister)

        val version = Utils().getVersionName(this)
        tvLoginVersion.text = getString(R.string.version, version)

        dbConnection = DapurClaraaDB.getDatabase(applicationContext)
        val toRegisterActivity = Intent(this, RegisterActivity::class.java)
        val toMenuActivity = Intent(this, MenuActivity::class.java)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        toMenuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        tvToRegister.setOnClickListener {
            startActivity(toRegisterActivity)
        }

        sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        alertDialogHelper = AlertDialogHelper(this)

        cvBtnLogin.setOnClickListener(View.OnClickListener {
            val valUser = tieLoginUsername.text
            val valPass = tieLoginPassword.text
            if (valUser.isNullOrEmpty()) {
                tieLoginUsername.error = "Username tidak boleh kosong"
                return@OnClickListener
            }
            if (valPass.isNullOrEmpty()) {
                tieLoginPassword.error = "Password tidak boleh kosong"
                return@OnClickListener
            }

            if (switchAdmin.isChecked) {
                adminViewModel.validateAdmin(
                    tieLoginUsername.text.toString(), tieLoginPassword.text.toString()
                )

            } else {
                customerViewModel.validateCustomer(
                    tieLoginUsername.text.toString(), tieLoginPassword.text.toString()
                )
            }
        })

        // Observe LiveData changes
        adminViewModel.admin.observe(this) { admin ->
            // Update your UI with the new data
            if (admin.adminName.isNotEmpty()) {
                // Save data
                sharedPreferencesHelper.username = admin.adminName
                sharedPreferencesHelper.isAdmin = true
                sharedPreferencesHelper.isLoggedIn = true

                Log.d(TAG, "Login Admin success ${admin.adminName}")

                startActivity(toMenuActivity)
            } else {
                alertDialogHelper.showAlertDialog(
                    "Login gagal", "User tidak ditemukan"
                )
            }
        }

        // Observe LiveData changes
        customerViewModel.customer.observe(this) { customer ->
            if (customer.customerName.isNotEmpty()) {

                // Save data
                sharedPreferencesHelper.username = customer.customerName
                sharedPreferencesHelper.userObj = Gson().toJson(customer)
                sharedPreferencesHelper.isAdmin = false
                sharedPreferencesHelper.isLoggedIn = true

                Log.d(TAG, "Login Customer success ${customer.customerName}")

                startActivity(toMenuActivity)
            } else {
                alertDialogHelper.showAlertDialog("Login gagal", "User tidak ditemukan")
            }
        }


    }
}