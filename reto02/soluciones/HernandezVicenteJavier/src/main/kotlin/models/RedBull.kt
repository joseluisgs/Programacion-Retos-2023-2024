package org.example.models

/**
 * Equipo RedBull
 */
abstract class RedBull(
    nombre: String = "RedBull",
    probFallo: Int = 0,
    probAccidente: Int,
    fila: Int,
    columna: Int,)
    :Pilotos(nombre, probFallo, probAccidente, fila, columna), `Vuelta rápida`
{
    //TODO añadir las interfaces vuelta rápida
}