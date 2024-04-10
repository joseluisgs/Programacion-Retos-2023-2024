package org.example.models

import java.time.LocalDate

class Enemigo (
    nombre: String,
    habilidad: String,
    ataque: Int,
    edad: Int,
    arma: String,
    created_at: LocalDate = LocalDate.now(),
    updated_at: LocalDate? = LocalDate.now(),
    is_deleted: Boolean? = false

): Personaje(nombre, habilidad, ataque, edad, arma, created_at, updated_at, is_deleted) {
    override fun toString(): String {
        return "Enemigo(Nombre: $nombre, Habilidad: $habilidad, Puntos de Ataque: $ataque, Edad: $edad, Arma: $arma, Fecha de Creación: $created_at, Fecha de actualización: $updated_at, Borrado Lógico: $is_deleted"
    }
}