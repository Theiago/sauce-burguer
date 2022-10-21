package br.com.mobile.sauceburguer

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    Lanches::class], version = 1)
abstract class SauceDatabase: RoomDatabase() {
    abstract fun lanchesDAO(): LanchesDAO
}