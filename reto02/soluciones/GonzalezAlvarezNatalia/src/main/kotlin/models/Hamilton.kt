package org.example.models

import org.example.interfaces.Mercedes

/**
 * Clase que representa al piloto "Hamilton" del juego.
 *
 * Hereda de la clase Piloto y de la interfaz Mercedes.
 *
 * @property i número de fila del piloto
 * @property j número de columna del piloto
 * @property nombre nombre del piloto
 * @property vueltas número de vueltas del piloto
 * @property continuar indicador de si los pilotos se mueven o no
 */
class Hamilton(
    nombre: String,
    i : Int = 6,
    j : Int = 0,
    vueltas: Int =0,
    continuar:Int=0
) : Piloto(i,j,nombre, vueltas, continuar), Mercedes {

    /**
     * Funcion que hace que Hamilton retroceda 1 casilla.
     *
     * @param pista matriz
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    override fun cometerError(pista: Array<Array<Any?>>) {
        if (continuar==0) {
            if ((0..100).random() < 20) {
                if (j == 0) {
                    j = pista[0].size - 1
                    pista[i][j] = Hamilton("Hamilton", 6, 0)
                    pista[i][0] = null
                } else {
                    pista[i][j] = null
                    if (j==pista.size-1)vueltas--
                    j--
                    pista[i][j] = Hamilton("Hamilton", 6, 0)
                    println("$nombre ha cometido un error y retrocede 1 casilla.")
                }
            }
        }
    }

}