package org.example.models.pilotos

import org.example.models.escuderias.AstonMartin
import org.example.models.base.Piloto

/**
 * Lance Stroll, Piloto de Aston Martin
 * @property tiempoPerdido tiempo perdido por mala estrategia
 */
class Stroll(nombre: String = "Stroll", override var tiempoPerdido: Int = 0) :
    AstonMartin(nombre, fila = 3, columna = 0, probAccidente = 10) {
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