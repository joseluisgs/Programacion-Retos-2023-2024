package org.example.models

import java.time.LocalDate

/**
 * Clase que representa un enemigo en un juego.
 * @param id El identificador único del enemigo.
 * @param nombre El nombre del enemigo.
 * @param habilidad La habilidad especial del enemigo.
 * @param ataque El valor de ataque del enemigo.
 * @param edad La edad del enemigo.
 * @param arma El arma que utiliza el enemigo.
 * @param createdAt La fecha de creación del enemigo, por defecto es la fecha actual.
 * @param updatedAt La fecha de última actualización del enemigo, por defecto es la fecha actual.
 * @param isDeleted Indica si el enemigo ha sido eliminado, por defecto es falso.
 * @constructor Crea un enemigo con los parámetros especificados.
 */
class Enemigo(
    id: Int,
    nombre: String,
    habilidad: String,
    ataque: Int,
    edad: Int,
    arma: String,
    createdAt: LocalDate = LocalDate.now(),
    updatedAt: LocalDate? = LocalDate.now(),
    isDeleted: Boolean? = false
) : Personaje(id, nombre, habilidad, ataque, edad, arma, createdAt, updatedAt, isDeleted) {

    /**
     * Devuelve una representación en cadena del enemigo.
     * @return Una cadena que contiene información sobre el enemigo.
     */
    override fun toString(): String {
        return "Enemigo(Id: $id, Nombre: $nombre, Habilidad: $habilidad, Puntos de Ataque: $ataque, Edad: $edad, Arma: $arma, Fecha de Creación: $createdAt, Fecha de Actualización: $updatedAt)"
    }
}