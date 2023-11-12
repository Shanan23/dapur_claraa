package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Pembayaran(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_pembayaran") var transactionId: Int = 0,
    @ColumnInfo(name = "Id_customer", defaultValue = "") var customerId: Int = 0,
    @ColumnInfo(name = "Id_kue", defaultValue = "") var cakeId: Int = 0,
    @ColumnInfo(name = "quantity", defaultValue = "") var transactionQuantity: Int = 0,
    @ColumnInfo(name = "total", defaultValue = "") var transactionTotal: Int = 0,
    @ColumnInfo(name = "Alamat", defaultValue = "") var transactionAddress: String = "",
    @ColumnInfo(name = "Nohp", defaultValue = "") var transactionPhone: String = "",
    @ColumnInfo(name = "waktu_dan_tanggal", defaultValue = "") var transactionDate: String = "",
    @ColumnInfo(name = "GambarPay", typeAffinity = ColumnInfo.BLOB) var image: ByteArray?,
    @ColumnInfo(name = "Ongkir_kue", defaultValue = "") var transactionDeliveryCost: String = ""
) {
    @Ignore
    var kueDetail: Kue = Kue(image = null)

    @Ignore
    var customerDetail: Customers = Customers()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pembayaran

        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        return image?.contentHashCode() ?: 0
    }
}