package com.bakery.dapurclaraa.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id_admin")
    var adminId: Int = 0,
    @ColumnInfo(name = "Username", defaultValue = "")
    var adminName: String = "",
    @ColumnInfo(name = "Password", defaultValue = "")
    var adminPass: String = ""
)