package com.bakery.dapurclaraa.helper

import android.util.Base64
import androidx.room.TypeConverter

object ByteArrayTypeConverter {
    @TypeConverter
    @JvmStatic
    fun byteArrayToString(byteArray: ByteArray?): String? {
        return byteArray?.let { Base64.encodeToString(it, Base64.DEFAULT) }
    }

    @TypeConverter
    @JvmStatic
    fun stringToByteArray(string: String?): ByteArray? {
        return string?.let { Base64.decode(it, Base64.DEFAULT) }
    }
}