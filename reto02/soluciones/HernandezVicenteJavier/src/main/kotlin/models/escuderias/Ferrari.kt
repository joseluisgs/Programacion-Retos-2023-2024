package org.example.models.escuderias

import org.example.models.acciones.Fiabilidad
import org.example.models.acciones.MalaEstrategia
import org.example.models.base.Piloto

/**
 * Escudería Ferrari
 */
abstract class Ferrari(nombre: String = "Ferrari", probFallo: Int = 10, probAccidente: Int, fila: Int, columna: Int) : Piloto(nombre, probFallo, probAccidente, fila, columna),
    MalaEstrategia, Fiabilidad {
}