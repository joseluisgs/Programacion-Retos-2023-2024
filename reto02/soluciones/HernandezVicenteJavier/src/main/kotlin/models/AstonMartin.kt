package org.example.models

/**
 * Equipo Aston Martin
 */
abstract class AstonMartin(
    nombre: String = "Aston Martin",
    probAccidente: Int,
    probFallo: Int = 5,
    fila: Int,
    columna: Int,
    )
    : Pilotos(nombre, probAccidente, probFallo, fila, columna), MalaEstrategia
{

}