package org.example.models.escuderias

import org.example.models.acciones.MalaEstrategia
import org.example.models.base.Piloto

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
    : Piloto(nombre, probAccidente, probFallo, fila, columna), MalaEstrategia
{

}