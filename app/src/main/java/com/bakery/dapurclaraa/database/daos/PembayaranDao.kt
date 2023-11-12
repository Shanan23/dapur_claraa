package com.bakery.dapurclaraa.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bakery.dapurclaraa.database.objects.Pembayaran

@Dao
interface PembayaranDao {
    @Query("SELECT * FROM Pembayaran")
    fun getAll(): List<Pembayaran>

    @Query("SELECT * FROM Pembayaran WHERE Id_pembayaran IN (:pembayaranIds)")
    fun loadAllByIds(pembayaranIds: IntArray): List<Pembayaran>

    @Query(
        "SELECT * FROM Pembayaran WHERE Id_customer = :customerId"
    )
    fun findByIdCustomer(customerId: String): List<Pembayaran>

    @Insert
    fun insertAll(vararg pembayaran: Pembayaran)

    @Delete
    fun delete(pembayaran: Pembayaran)
}