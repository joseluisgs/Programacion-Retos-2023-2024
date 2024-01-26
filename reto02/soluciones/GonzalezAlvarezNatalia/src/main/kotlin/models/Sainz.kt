package org.example.models

import org.example.interfaces.Ferrari
import org.example.interfaces.RedBull

/**
 * Clase que representa al piloto "Sainz" del juego.
 *
 * Hereda de la clase Piloto y de la interfaz Ferrari y RedBull.
 *
 * @property i número de fila del piloto
 * @property j número de columna del piloto
 * @property nombre nombre del piloto
 * @property vueltas número de vueltas del piloto
 * @property continuar indicador de si los pilotos se mueven o no
 */
class Sainz(
    nombre: String,
    i : Int = 4,
    j : Int = 0,
    vueltas: Int =0,
    continuar:Int=0
) : Piloto(i,j,nombre, vueltas, continuar), Ferrari, RedBull {

    /**
     * Funcion que hace que Sainz sea descalificado.
     *
     * @param pista matriz
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    override fun problemaFiabilidad(pista: Array<Array<Any?>>) :Boolean {
        pista[this.i][this.j] = Estado.DESCALIFICADO
        continuar=70
        println("Ha habido un problema de fiabilidad en Ferrari y $nombre ha sido descalificado.")
        return true
    }

    /**
     * Funcion que hace que Sainz avance 2 casillas.
     *
     * @param pista matriz
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    override fun vueltaRapida(pista: Array<Array<Any?>>) {
        if (continuar==0) {
            if ((0..100).random() < 10) {
                if (j == pista[0].size - 1) {
                    j = 1
                    pista[i][j] = Sainz("Sainz", 4, 0)
                    pista[i][pista[0].size - 1] = null
                } else {
                    pista[i][j] = null
                    j += 1
                    if (j >= pista[0].size) {
                        j = 1
                    }
                    pista[i][j] = Sainz("Sainz", 4, 0)
                    println("$nombre ha comenzado su vuelta rápida y avanza 2 casillas.")
                }
            }
        }
    }
}