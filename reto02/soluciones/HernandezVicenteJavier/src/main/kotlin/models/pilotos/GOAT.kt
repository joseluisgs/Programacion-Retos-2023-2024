package org.example.models.pilotos

import org.example.models.escuderias.AstonMartin
import org.example.models.base.Piloto

/**
 * Fernando Alonso, Piloto de Aston Martin
 * @property tiempoPerdido tiempo perdido debido a mala estrategia
 */
class GOAT(nombre: String = "Alonso", override var tiempoPerdido: Int = 0) :
    AstonMartin(nombre, fila = 2, columna = 0, probAccidente = 1) {
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