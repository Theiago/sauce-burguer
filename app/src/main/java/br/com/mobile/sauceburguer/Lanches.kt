package br.com.mobile.sauceburguer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "lanches")
class Lanches: Serializable {

    @PrimaryKey
    var id: Long? = null
    var nome: String = ""
    var preco: String = ""
    var peso_carne: String = ""
    var foto: String = ""

}