package com.bakery.dapurclaraa.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bakery.dapurclaraa.database.objects.Admin

@Dao
interface AdminDao {
    @Query("SELECT * FROM Admin")
    fun getAll(): List<Admin>

    @Query("SELECT * FROM Admin WHERE Id_admin IN (:adminIds)")
    fun loadAllByIds(adminIds: IntArray): List<Admin>

    @Query(
        "SELECT * FROM Admin WHERE Username LIKE :name LIMIT 1"
    )
    fun findByName(name: String): Admin

    @Query(
        "SELECT * FROM Admin WHERE Username LIKE :name AND Password LIKE :pass LIMIT 1"
    )
    fun validateAdmin(name: String, pass: String): Admin?

    @Delete
    fun delete(admin: Admin)

    @Insert
    fun insert(admin: Admin)
}