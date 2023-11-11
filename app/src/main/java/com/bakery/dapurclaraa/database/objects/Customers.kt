package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customers(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_customer") val customerId: Int,
    @ColumnInfo(name = "Nama_cust", defaultValue = "") val customerName: String,
    @ColumnInfo(name = "NoHp", defaultValue = "") val customerPhone: String,
    @ColumnInfo(name = "Alamat", defaultValue = "") val customerAddress: String,
    @ColumnInfo(name = "Password", defaultValue = "") val customerPassword: String
)