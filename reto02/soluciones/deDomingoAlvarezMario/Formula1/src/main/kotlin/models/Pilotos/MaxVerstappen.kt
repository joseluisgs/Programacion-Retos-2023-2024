package org.example.models.Pilotos

import org.example.models.Patrocinadores.RedBull

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @param i Coordenadas de la fila
 * @param j Coordenadas de la columna
 */
class MaxVerstappen (i: Int, j: Int):
    Pilotos(" 🟢 ","Max Verstappen",5, i, j), RedBull{

    /**
     * Metodo de vuelta rapida en caso de que se da el porcentaje avanza 2 casillas
     */
    override fun vueltaRapida() {
        if (j != 9){
            val prov = (1..100).random()
            if (prov<=10){
                j++
            }
        }
    }
    override fun accion() {
        if(!accidente){
            if (moverse == 0){
                movimiento()
                accidente()
                vueltaRapida()
            } else {moverse -= 1}
        }
    }

}