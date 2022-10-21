package br.com.mobile.sauceburguer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LanchesDAO {

    @Query("SELECT * FROM lanches where id = :id")
    fun getById(id: Long): Lanches?

    @Query("SELECT * FROM lanches")
    fun findAll(): List<Lanches>

    @Insert
    fun insert(lanches: Lanches)

    @Delete
    fun delete(lanches: Lanches)
}