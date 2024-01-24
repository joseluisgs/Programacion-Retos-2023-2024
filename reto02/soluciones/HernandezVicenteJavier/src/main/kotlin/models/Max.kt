package org.example.models

/**
 * Max Verstappen, piloto de RedBull
 * @property probVueltaRapida probabilidad de realizar una vuelta rápida
 */
class Max(nombre: String = "Verstappen", override val probVueltaRapida: Int = 10) :RedBull(nombre, fila = 0, columna = 0, probAccidente = 1){
}