package br.com.mobile.sauceburguer

import androidx.room.Room

object DatabaseManager {
    private var dbInstance: SauceDatabase

    init {
        val contexto = SauceApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            contexto,
            SauceDatabase::class.java,
            "sauce.sqlite"
        ).build()
    }

    fun getLanchesDAO(): LanchesDAO {
        return dbInstance.lanchesDAO()
    }

}