package org.example.models

/**
 * Equipo Mercedes
 */
abstract class Mercedes(
    nombre: String = "Mercedes",
    probAccidente: Int,
    probFallo: Int = 10,
    fila: Int,
    columna: Int,)
    :Pilotos(nombre, probAccidente, probFallo, fila, columna), SafetyCar {
}