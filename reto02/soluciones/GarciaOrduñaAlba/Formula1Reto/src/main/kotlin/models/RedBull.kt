package org.example.models

/**
 * Interfaz que define la capacidad de un piloto del equipo Red Bull para realizar una vuelta rápida.
 */
interface RedBull {
    /**
     * Método que simula la acción de realizar una vuelta rápida en una determinada vuelta de la carrera.
     * Puede ser implementado por clases que implementen esta interfaz.
     *
     * @param vuelta Número de la vuelta en la que se realiza la acción.
     */
    fun realizarVueltaRapida(vuelta: Int)
}