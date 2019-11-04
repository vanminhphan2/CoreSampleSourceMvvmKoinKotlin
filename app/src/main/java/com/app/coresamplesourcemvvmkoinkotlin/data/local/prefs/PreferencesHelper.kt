package com.app.coresamplesourcemvvmkoinkotlin.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper constructor(context: Context, prefFileName: String) {

    private val PREF_KEY_TOKEN = "PREF_KEY_TOKEN"

    private val PREF_KEY_USER_ID = "PREF_KEY_USER_ID"

    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    fun getLoginToken(): String? {
        return mPrefs.getString(PREF_KEY_TOKEN, "")
    }

    fun setLoginToken(token: String) {
        mPrefs.edit().putString(PREF_KEY_TOKEN, token).apply()
    }

    fun getUserId(): Int {
        return mPrefs.getInt(PREF_KEY_USER_ID, -1)
    }

    fun setUserId(id: Int) {
        mPrefs.edit().putInt(PREF_KEY_USER_ID, id).apply()
    }
}