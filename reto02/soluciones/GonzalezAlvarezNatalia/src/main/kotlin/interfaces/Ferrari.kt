package org.example.interfaces

/**
 * Interfaz que representa a la escudería Ferrari del juego
 */
interface Ferrari {
    /**
     * Funcion que hace que el piloto sea descalificado.
     *
     * @param pista matriz
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun problemaFiabilidad(pista: Array<Array<Any?>>): Boolean
}