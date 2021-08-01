package com.riquest.watchaw.utils

import android.content.Context
import android.content.SharedPreferences
import com.riquest.watchaw.utils.Preferences.Companion.USER_PREFF

class Preferences(val context: Context) {
    companion object{
        const val USER_PREFF = "USER_PREFF"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREFF, 0)

    fun setValues(key : String, value : String){
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValues(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }
}

