package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_admin") val customerId: Int,
    @ColumnInfo(name = "Username", defaultValue = "") val customerName: String,
    @ColumnInfo(name = "Password", defaultValue = "") val customerPhone: String
)