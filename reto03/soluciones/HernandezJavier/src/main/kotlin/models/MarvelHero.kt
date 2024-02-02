package org.example.models

import org.example.models.Base.Hero
import java.time.LocalDateTime

class MarvelHero(
    id: Int = 0,
    nombre: String,
    alias: String,
    altura: Int,
    edad: Int,
    notas: String,
    updatedAt: LocalDateTime,
    createdAt: LocalDateTime
) : Hero(id, nombre, alias, altura, edad, notas, updatedAt, createdAt) {

    override fun toString(): String {
        return "Marvel (Nombre: $nombre, Alias: $alias, Altura: $altura, Edad: $edad, Notas: $notas"
    }
}