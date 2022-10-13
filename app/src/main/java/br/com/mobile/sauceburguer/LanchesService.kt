package br.com.mobile.sauceburguer

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object LanchesService
{

    val listaLanches = """[
    {
        "nome": "Chicken",
        "foto": "https://www.goomer.app/webmenu/sauce-burger-house/product/8289358/picture/large/220923221554",
        "id": 1,
        "preco": "R${'$'} 18.00",
        "peso_carne": "150g"
    },
    {
        "nome": "Smash Duplo",
        "foto": "https://www.goomer.app/webmenu/sauce-burger-house/product/6722123/picture/medium/220726202035",
        "id": 2,
        "preco": "R${'$'} 30.00",
        "peso_carne": "300g"
    },
    {
        "nome": "Veggie",
        "foto": "https://www.goomer.app/webmenu/sauce-burger-house/product/7505001/picture/large/221001222221",
        "id": 3,
        "preco": "R${'$'} 26.00",
        "peso_carne": "170g"
    },
    {
        "nome": "Coca-Cola",
        "foto": "https://www.goomer.app/webmenu/sauce-burger-house/product/6755130/picture/large/220129014232",
        "id": 4,
        "preco": "R${'$'} 5.00",
        "peso_carne": "350ml"
    }
]"""


    //val host = "http://172.26.176.1:5500"
    val TAG = "WS_LMSApp"

    fun getLanches(): List<Lanches> {
        var lanches = mutableListOf<Lanches>()

        //val url = "$host/lanches.json"
        //val json = HttpHelper.get(url)

        lanches = parseJson<MutableList<Lanches>>(listaLanches)

        //Log.d(TAG, json)

        return lanches
    }

    fun saveDisciplina(lanches: Lanches) {
        var json = GsonBuilder().create().toJson(lanches)
        //HttpHelper.post("$host/disciplinas", json)
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}