package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pembayaran(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_pembayaran") var transactionId: Int = 0,
    @ColumnInfo(name = "Id_customer", defaultValue = "") var customerId: Int = 0,
    @ColumnInfo(name = "Id_kue", defaultValue = "") var cakeId: Int = 0,
    @ColumnInfo(name = "Alamat", defaultValue = "") var transactionAddress: String = "",
    @ColumnInfo(name = "Nohp", defaultValue = "") var transactionPhone: String = "",
    @ColumnInfo(name = "waktu_dan_tanggal", defaultValue = "") var transactionDate: String = "",
    @ColumnInfo(name = "Ongkir_kue", defaultValue = "") var transactionDeliveryCost: String = ""
)