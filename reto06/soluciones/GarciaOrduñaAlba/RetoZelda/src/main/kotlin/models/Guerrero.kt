package org.example.models

import java.time.LocalDate

/**
 * Clase que representa un guerrero en un juego.
 * @param id El identificador único del guerrero.
 * @param nombre El nombre del guerrero.
 * @param habilidad La habilidad especial del guerrero.
 * @param ataque El valor de ataque del guerrero.
 * @param edad La edad del guerrero.
 * @param arma El arma que utiliza el guerrero.
 * @param createdAt La fecha de creación del guerrero, por defecto es la fecha actual.
 * @param updatedAt La fecha de última actualización del guerrero, por defecto es la fecha actual.
 * @param isDeleted Indica si el guerrero ha sido eliminado, por defecto es falso.
 * @constructor Crea un guerrero con los parámetros especificados.
 */
class Guerrero(
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
     * Devuelve una representación en cadena del guerrero.
     * @return Una cadena que contiene información sobre el guerrero.
     */
    override fun toString(): String {
        return "Guerrero(Id: $id, Nombre: $nombre, Habilidad: $habilidad, Puntos de Ataque: $ataque, Edad: $edad, Arma: $arma, Fecha de Creación: $createdAt, Fecha de Actualización: $updatedAt)"
    }
}