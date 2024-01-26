package org.example.models.acciones

import org.example.models.base.Piloto

/**
 * Mala estrategía que hará parar a los pilotos durante un periodo de tiempo
 * @property tiempoPerdido el tiempo que perderan
 */
interface MalaEstrategia {
    var tiempoPerdido: Int

    /**
     * Si el piloto se encuentra en pista corriendo, si se cumple la probabilidad de fallo,
     * estos sufriran una perdida de tiempo de 2 segundos
     * @param piloto el piloto
     */
    fun cagada(piloto: Piloto){
        if(!piloto.finCarrera && !piloto.dnf){
            if((0..100).random() < piloto.probFallo){
                println("Ese undercut no ha sido de manual y hace perder tiempo a ${piloto.nombre}, Antonio")
                tiempoPerdido = 2
            }
        }
    }
}