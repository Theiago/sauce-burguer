package br.com.mobile.sauceburguer

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object HttpHelper {

    fun get(url: String): String {
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    fun post(url: String, json: String): String {
        var tipo = "application/json; charset=utf-8".toMediaType()
        var body = RequestBody.create(tipo, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    fun delete(url: String): String {
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }

    var client = OkHttpClient()

    private fun getJson(request: Request): String {
        val response = client.newCall(request).execute()
        val body = response.body
        var json = body!!.string()
        return json
    }

}