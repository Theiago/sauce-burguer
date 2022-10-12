package br.com.mobile.sauceburguer

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object LanchesService
{

    val host = "https://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getLanches(): List<Lanches> {
        var lanches = mutableListOf<Lanches>()

        val url = "$host/disciplinas"
        val json = HttpHelper.get(url)

        lanches = parseJson<MutableList<Lanches>>(json)

        Log.d(TAG, json)

        return lanches
    }

    fun saveDisciplina(lanches: Lanches) {
        var json = GsonBuilder().create().toJson(lanches)
        HttpHelper.post("$host/disciplinas", json)
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}