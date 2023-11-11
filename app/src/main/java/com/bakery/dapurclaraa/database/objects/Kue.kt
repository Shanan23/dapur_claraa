package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kue(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_kue") val cakeId: Int,
    @ColumnInfo(name = "Id_pembayaran", defaultValue = "") val transactionId: Int,
    @ColumnInfo(name = "Id_customer", defaultValue = "") val customerId: Int,
    @ColumnInfo(name = "Jenis_kue", defaultValue = "") val cakeType: String,
    @ColumnInfo(name = "Nama_kue", defaultValue = "") val cakeName: String,
    @ColumnInfo(name = "Harga_kue", defaultValue = "") val cakePrice: String,
    @ColumnInfo(name = "Stok_kue", defaultValue = "") val cakeStock: String,
    @ColumnInfo(name = "GambarKue", typeAffinity = ColumnInfo.BLOB) val image: Byte?,
    @ColumnInfo(name = "Ongkir_kue", defaultValue = "") val cakeDeliveryCost: Int,
    @ColumnInfo(name = "Ukuran_kue", defaultValue = "") val cakeSize: String
)