package br.com.mobile.sauceburguer

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object CarrinhoService {
    val host = "https://sauceburguer.herokuapp.com/api/v1"
    val carrinho = mutableListOf<String>()

    fun addCarrinho(carrinho: Carrinho) {
        var json = GsonBuilder().create().toJson(carrinho)
        HttpHelper.post("$host/carrinho", json)

    }

    fun addCozinha(carrinho: MutableList<String>) {
        var json = GsonBuilder().create().toJson(carrinho)
        HttpHelper.post("$host/cozinha", json)
    }

    fun listaCarrinho(): List<Carrinho> {
        var carrinho = mutableListOf<Carrinho>()

        val url = "${LanchesService.host}/carrinho"
        val json = HttpHelper.get(url)

        carrinho = parseJson<MutableList<Carrinho>>(json)

        Log.d("Carrinho", json)

        return carrinho
    }

    fun deleteCarrinho(id: String) {
            HttpHelper.delete("${LanchesService.host}/carrinho/id")
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}