package org.example.models

import java.time.LocalDateTime

abstract class SuperHero (
    var id:Int = 0,
    var nombre:String = "",
    var alias:String = "",
    var altura:Int = 180,
    var notas:String ="",
) {

    val createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()


    override fun toString(): String {

        val cad = StringBuilder()

        cad.append("[ID = $id] Nombre: $nombre | Alias: $alias | Altura: $altura | ")
        cad.append("Fecha alta: $createdAt | Última actualización: $updatedAt\n")

        return cad.toString()
    }
}