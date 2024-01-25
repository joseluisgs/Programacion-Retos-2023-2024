package org.example.models.pilotos

import org.example.models.escuderias.RedBull

/**
 * Checo Pérez, piloto de Redbull
 * @property probVueltaRapida la probabilidad de realizar una vuelta rápida
 */
class Checo(nombre: String = "Checo", override val probVueltaRapida: Int = 10) :
    RedBull(nombre, fila = 1, columna = 0, probAccidente = 5) {
}