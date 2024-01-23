package org.example.models.Pilotos

import org.example.models.Patrocinadores.AstonMartin

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @param i Coordenadas de la fila
 * @param j Coordenadas de la columna
 */
class FernandoAlonso(i: Int, j: Int):Pilotos(" 🟣 ","Fernando Alonso",5, i, j), AstonMartin{
    override fun accion() {
        if(!accidente){
            if (moverse == 0){
                movimiento()
                accidente()
                malaEstrategia()
            } else {moverse -= 1}
        }
    }

    /**
     * si se da el caso no se mueven cada dos segs
     */
    override fun malaEstrategia(){
        val prov = (1..100).random()
        if (prov <=15){
            moverse=2
            println("Mala estrategia: $nombre se detiene por 2 segundos")
        }
    }
}