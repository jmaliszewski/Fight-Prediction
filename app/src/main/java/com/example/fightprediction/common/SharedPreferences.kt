package com.example.fightprediction.common

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val preferences = context.getSharedPreferences(ApiConstants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    fun getStoredTag(tag: String): String{
        return preferences.getString(tag, "")!!
    }

    fun setStoredTag(tag: String, value: String){
        preferences.edit().putString(tag, value).apply()
    }

}