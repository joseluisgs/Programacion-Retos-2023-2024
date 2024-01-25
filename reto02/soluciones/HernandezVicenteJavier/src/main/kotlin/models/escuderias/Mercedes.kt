package org.example.models.escuderias

import org.example.models.base.Piloto
import org.example.models.acciones.SafetyCar

/**
 * Equipo Mercedes
 */
abstract class Mercedes(
    nombre: String = "Mercedes",
    probAccidente: Int,
    probFallo: Int = 10,
    fila: Int,
    columna: Int,)
    : Piloto(nombre, probAccidente, probFallo, fila, columna), SafetyCar {
}