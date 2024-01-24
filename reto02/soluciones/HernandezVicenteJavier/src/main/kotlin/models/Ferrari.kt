package org.example.models

/**
 * Escudería Ferrari
 */
abstract class Ferrari(nombre: String = "Ferrari", probFallo: Int = 10, probAccidente: Int, fila: Int, columna: Int) : Pilotos(nombre, probFallo, probAccidente, fila, columna), MalaEstrategia, Fiabilidad{
}