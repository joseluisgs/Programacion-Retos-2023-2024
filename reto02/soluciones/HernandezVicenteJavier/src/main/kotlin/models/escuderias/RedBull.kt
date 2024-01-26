package org.example.models.escuderias

import org.example.models.base.Piloto
import org.example.models.acciones.`Vuelta rápida`

/**
 * Equipo RedBull
 */
abstract class RedBull(
    nombre: String = "RedBull",
    probFallo: Int = 0,
    probAccidente: Int,
    fila: Int,
    columna: Int,)
    : Piloto(nombre, probFallo, probAccidente, fila, columna), `Vuelta rápida`
{
    //TODO añadir las interfaces vuelta rápida
}