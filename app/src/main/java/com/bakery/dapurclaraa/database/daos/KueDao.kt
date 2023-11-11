package com.bakery.dapurclaraa.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bakery.dapurclaraa.database.objects.Kue

@Dao
interface KueDao {
    @Query("SELECT * FROM Kue")
    fun getAll(): List<Kue>

    @Query("SELECT * FROM Kue WHERE Id_kue IN (:kueIds)")
    fun loadAllByIds(kueIds: IntArray): List<Kue>

    @Query(
        "SELECT * FROM Kue WHERE Nama_kue LIKE :name LIMIT 1")
    fun findByName(name: String): Kue

    @Insert
    fun insertAll(vararg kue: Kue)

    @Delete
    fun delete(kue: Kue)
}