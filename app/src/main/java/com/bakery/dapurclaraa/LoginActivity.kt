package com.bakery.dapurclaraa

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import com.bakery.dapurclaraa.database.DapurClaraaDB
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.helper.AlertDialogHelper
import com.bakery.dapurclaraa.helper.SharedPreferencesHelper
import com.bakery.dapurclaraa.viewmodels.AdminViewModel
import com.bakery.dapurclaraa.viewmodels.CustomerViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var admin: Admin
    private lateinit var alertDialogHelper: AlertDialogHelper
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var dbConnection: DapurClaraaDB
    private lateinit var tieLoginUsername: TextInputEditText
    private lateinit var tieLoginPassword: TextInputEditText
    private lateinit var switchAdmin: SwitchCompat
    private lateinit var cvBtnLogin: CardView
    private val adminViewModel: AdminViewModel by viewModels()
    private val customerViewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        tieLoginUsername = findViewById(R.id.tieLoginUsername)
        tieLoginPassword = findViewById(R.id.tieLoginPassword)
        switchAdmin = findViewById(R.id.switchAdmin)
        cvBtnLogin = findViewById(R.id.cvBtnLogin)

        dbConnection = DapurClaraaDB.getDatabase(applicationContext)

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
                sharedPreferencesHelper.isAdmin = false
                sharedPreferencesHelper.isLoggedIn = true
            } else {
                alertDialogHelper.showAlertDialog("Login gagal", "User tidak ditemukan")
            }
        }
    }
}