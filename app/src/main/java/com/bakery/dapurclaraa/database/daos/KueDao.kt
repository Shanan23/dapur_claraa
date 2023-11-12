package com.bakery.dapurclaraa.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.dapurclaraa.database.objects.Kue

@Dao
interface KueDao {
    @Query("SELECT * FROM Kue")
    fun getAll(): List<Kue>

    @Query("SELECT Jenis_kue FROM Kue GROUP BY Jenis_kue")
    fun getAllType(): List<String>

    @Query("SELECT * FROM Kue WHERE Id_kue IN (:kueIds)")
    fun loadAllByIds(kueIds: IntArray): List<Kue>

    @Query(
        "SELECT * FROM Kue WHERE Nama_kue LIKE :name LIMIT 1"
    )
    fun findByName(name: String): Kue?

    @Query(
        "SELECT * FROM Kue WHERE Jenis_kue LIKE :type"
    )
    fun findByType(type: String): List<Kue>

    @Insert
    fun insertAll(kue: List<Kue>)

    @Upsert
    fun upsertAll(kue: List<Kue>)

    @Delete
    fun delete(kue: Kue)

    @Delete
    fun deleteAll(kue: List<Kue>)
}