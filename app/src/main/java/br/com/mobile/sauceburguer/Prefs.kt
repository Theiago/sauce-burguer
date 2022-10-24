package br.com.mobile.sauceburguer

import android.content.SharedPreferences

object Prefs {
    private fun prefs(): SharedPreferences {
        var contexto = SauceApplication.getInstance().applicationContext
        return contexto.getSharedPreferences("Sauce", 0)
    }

    fun setBoolean(flag: String, valor: Boolean) {
        prefs().edit().putBoolean(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean {
        return prefs().getBoolean(flag, false)
    }

    fun setString(flag: String, valor: String) {
        prefs().edit().putString(flag, valor).apply()
    }

    fun getString(flag: String): String {
        return prefs().getString(flag, "")!!
    }

    fun setInt(flag: String, valor: Int) {
        prefs().edit().putInt(flag, valor).apply()
    }

    fun getInt(flag: String): Int {
        return prefs().getInt(flag, 0)!!
    }
}