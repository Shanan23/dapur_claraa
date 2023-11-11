package com.bakery.dapurclaraa.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bakery.dapurclaraa.database.objects.Customers

@Dao
interface CustomersDao {
    @Query("SELECT * FROM Customers")
    fun getAll(): List<Customers>

    @Query("SELECT * FROM Customers WHERE Id_customer IN (:customerIds)")
    fun loadAllByIds(customerIds: IntArray): List<Customers>

    @Query(
        "SELECT * FROM Customers WHERE Nama_cust LIKE :name LIMIT 1")
    fun findByName(name: String): Customers

    @Insert
    fun insertAll(vararg customers: Customers)

    @Delete
    fun delete(customers: Customers)
}