package br.com.mobile.sauceburguer

import android.util.Log
import androidx.room.Database
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object LanchesService
{
    val host = "https://sauceburguer.herokuapp.com/api/v1"
    val TAG = "WS_LMSApp"

    fun getLanches(): List<Lanches> {
        try {
            var lanches = mutableListOf<Lanches>()

            val url = "$host/lanches"
            val json = HttpHelper.get(url)

            lanches = parseJson<MutableList<Lanches>>(json)

            Log.d(TAG, json)

            return lanches
        } catch (ex: Exception) {
            var lanches = DatabaseManager.getLanchesDAO().findAll()
            return lanches
        }
    }

    fun saveLanche(lanches: Lanches) {
        try {
        var json = GsonBuilder().create().toJson(lanches)
            HttpHelper.post("$host/lanches", json)
         } catch (ex: Exception) {
            DatabaseManager.getLanchesDAO().insert(lanches)
         }

    }

    fun deleteLanche(lanches: Lanches) {
        try {
            HttpHelper.delete("$host/lanches/${lanches.id}")
        } catch (ex: Exception) {
            DatabaseManager.getLanchesDAO().delete(lanches)
        }
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}