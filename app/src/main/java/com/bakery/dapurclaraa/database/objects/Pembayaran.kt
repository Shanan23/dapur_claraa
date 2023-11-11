package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pembayaran(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_pembayaran") val transactionId: Int,
    @ColumnInfo(name = "Id_customer", defaultValue = "") val customerId: Int,
    @ColumnInfo(name = "Id_kue", defaultValue = "") val cakeId: Int,
    @ColumnInfo(name = "Alamat", defaultValue = "") val transactionAddress: String,
    @ColumnInfo(name = "Nohp", defaultValue = "") val transactionPhone: String,
    @ColumnInfo(name = "waktu_dan_tanggal", defaultValue = "") val transactionDate: String,
    @ColumnInfo(name = "Ongkir_kue", defaultValue = "") val transactionDeliveryCost: String
)