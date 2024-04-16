package org.example.models

import java.time.LocalDate

class Guerrero(
    id: Int,
    nombre: String,
    habilidad: String,
    ataque: Int,
    edad: Int,
    arma: String,

    createdAt: LocalDate = LocalDate.now(),
    updatedAt: LocalDate? = LocalDate.now(),
    isDeleted: Boolean? = false)

    : Personaje(id, nombre, habilidad, ataque, edad, arma, createdAt, updatedAt, isDeleted)
{
    override fun toString(): String {
        return "Guerrero(Id: $id, Nombre: $nombre, Habilidad: $habilidad, Puntos de Ataque: $ataque, Edad: $edad, Arma: $arma, Fecha de Creación: $createdAt, Fecha de actualización: $updatedAt"
    }
}
