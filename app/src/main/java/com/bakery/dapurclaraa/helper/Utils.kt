package com.bakery.dapurclaraa.helper

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class Utils {
    fun getVersionName(context: Context): String {
        try {
            val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }
}