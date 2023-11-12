package com.bakery.dapurclaraa.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    companion object {
        private const val PREF_NAME = "MyAppPreferences"
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_IS_ADMIN = "isAdmin"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var username: String?
        get() = sharedPreferences.getString(KEY_USERNAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_USERNAME, value).apply()

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    var isAdmin: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_ADMIN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_ADMIN, value).apply()

    // Add other preference methods as needed
}