package org.example.models.Pilotos

import org.example.models.Patrocinadores.AstonMartin
import org.example.models.Patrocinadores.Ferrari

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @param i Coordenadas de la fila
 * @param j Coordenadas de la columna
 */
class LeClerc(i: Int, j: Int) : Pilotos(" 🟠 ","Le Clerc",20, i, j),AstonMartin,Ferrari{
    override fun accion() {
        if(!accidente){
            if (moverse == 0){
                movimiento()
                escuderia()
            } else {moverse -= 1}
        }
    }

    /**
     * Segun probavilidad te leva a alguna de las dos funciones de dos escuderias distintas
     */
    private fun escuderia(){
        val random = (1..5).random()
        if (random == 1){
            problemasConfiabilidad()
        }
        if(random == 2){
            malaEstrategia()
        }
    }

    /**
     * En este caso el piloto es retirado de la carrera
     */
    override fun problemasConfiabilidad(){
        accidente = true
        emji = " ❌ "
        println("Problemas de Confiabilidad:$nombre queda fuera de la carrera")
    }

    /**
     * Si se da el caso no se mueven cada dos segs
     */
    override fun malaEstrategia(){
        val prov = (1..100).random()
        if (prov <=15){
            moverse=2
            println("Mala estrategia: $nombre se detiene por 2 segundos")
        }
    }
}