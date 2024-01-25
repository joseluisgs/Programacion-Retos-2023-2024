package org.example.models.pilotos

import org.example.models.escuderias.Ferrari
import org.example.models.base.Piloto

/**
 * Carlos Sainz CDT, Piloto de Ferrari
 * @property tiempoPerdido el tiempo que perderá por mala estrategia
 * @property fiabilidad la probabilidad de sufrir fallos de fiabilidad
 */
class Sainz(nombre: String = "Sainz", override var tiempoPerdido: Int = 0, override var fiabilidad: Int = 10) : Ferrari(nombre, fila = 6, columna = 0, probAccidente = 5) {
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