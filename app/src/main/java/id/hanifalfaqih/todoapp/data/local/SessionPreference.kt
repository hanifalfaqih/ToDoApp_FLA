package id.hanifalfaqih.todoapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SessionPreference(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    fun saveToken(token: String) {
        preferences.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    fun saveName(name: String) {
        preferences.edit()
            .putString(KEY_NAME, name)
            .apply()
    }

    fun getName(): String? {
        return preferences.getString(KEY_NAME, "User")
    }

    fun clearSession() {
        preferences.edit()
            .clear()
            .apply()
    }

    companion object {
        private const val PREF_NAME = "todo_app_pref"
        private const val KEY_TOKEN = "key_token"
        private const val KEY_NAME = "key_name"
    }
}