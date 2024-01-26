package org.example.models

import org.example.interfaces.AstonMartin

/**
 * Clase que representa al piloto "Alonso" del juego.
 *
 * Hereda de la clase Piloto y de la interfaz AstonMartin.
 *
 * @property i número de fila del piloto
 * @property j número de columna del piloto
 * @property nombre nombre del piloto
 * @property vueltas número de vueltas del piloto
 * @property continuar indicador de si los pilotos se mueven o no
 */
class Alonso(
    nombre: String,
    i : Int = 2,
    j : Int = 0,
    vueltas:Int = 0,
    continuar:Int = 0
) : Piloto(i,j,nombre, vueltas,continuar), AstonMartin {

    /**
     * Funcion que posiciona a Alonso en la misma posicion durante 2 segundos
     *
     * @param pista matriz
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    override fun malaEstrategia(pista: Array<Array<Any?>>) {
        if (continuar==0) {
            if ((0..100).random() < 15) {
                if (continuar == 0) {
                    continuar = 2
                    pista[i][j] = Alonso("Alonso", 2, 0)
                }
                println("$nombre ha realizado una mala estrategia y ha parado 2 segundos.")
            }
        }
    }
}