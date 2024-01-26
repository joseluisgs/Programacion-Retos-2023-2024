package org.example.models.pilotos

import org.example.models.escuderias.Ferrari
import org.example.models.base.Piloto

/**
 * Charles LeClerc, Piloto de Ferrari
 * @property tiempoPerdido el tiempo que perderá por mala estrategia
 * @property fiabilidad la probabilidad de sufrir fallos de fiabilidad
 */
class LeClerc(nombre: String = "LeClerc", override var tiempoPerdido: Int = 0, override var fiabilidad: Int = 10) :
    Ferrari(nombre, fila = 7, columna = 0, probAccidente = 10) {
    /**
     * El piloto se para durante el tiempo perdido
     */
    fun estratgia(pista: Array<Array<Piloto?>>) {
        cagada(this)
        if(tiempoPerdido > 0){
            tiempoPerdido--
        }else if(tiempoPerdido == 0){
            moverse(pista)
        }
    }
}