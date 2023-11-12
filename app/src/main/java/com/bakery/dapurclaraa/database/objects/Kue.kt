package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bakery.dapurclaraa.helper.ByteArrayTypeConverter

@Entity
@TypeConverters(ByteArrayTypeConverter::class)
data class Kue(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_kue") var cakeId: Int = 0,
    @ColumnInfo(name = "Id_pembayaran", defaultValue = "") var transactionId: Int = 0,
    @ColumnInfo(name = "Id_customer", defaultValue = "") var customerId: Int = 0,
    @ColumnInfo(name = "Jenis_kue", defaultValue = "") var cakeType: String = "",
    @ColumnInfo(name = "Nama_kue", defaultValue = "") var cakeName: String = "",
    @ColumnInfo(name = "Harga_kue", defaultValue = "") var cakePrice: String = "",
    @ColumnInfo(name = "Stok_kue", defaultValue = "") var cakeStock: String = "",
    @ColumnInfo(name = "GambarKue", typeAffinity = ColumnInfo.BLOB) var image: ByteArray?,
    @ColumnInfo(name = "Ongkir_kue", defaultValue = "") var cakeDeliveryCost: Int = 0,
    @ColumnInfo(name = "Ukuran_kue", defaultValue = "") var cakeSize: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Kue

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