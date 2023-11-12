package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customers(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_customer") var customerId: Int = 0,
    @ColumnInfo(name = "Username", defaultValue = "") var customerUsername: String = "",
    @ColumnInfo(name = "Nama_cust", defaultValue = "") var customerName: String = "",
    @ColumnInfo(name = "NoHp", defaultValue = "") var customerPhone: String = "",
    @ColumnInfo(name = "Alamat", defaultValue = "") var customerAddress: String = "",
    @ColumnInfo(name = "Password", defaultValue = "") var customerPassword: String = ""
)