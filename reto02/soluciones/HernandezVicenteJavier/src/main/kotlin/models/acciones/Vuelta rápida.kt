package org.example.models.acciones

import org.example.models.base.Piloto
import org.example.controllers.Pista

/**
 * Posibilidad de realizar una vuelta rápida haciendo que el piloto avance 2 posiciones
 * @property probVueltaRapida la probabilidad de realizar una vuelta rápida
 */
interface `Vuelta rápida` {
    val probVueltaRapida: Int

    /**
     * Si el piloto se encuentra corriendo y se cumple la probabilidad de vuelta rápida, el piloto avanzará 2 posiciones
     * cuando esté terminando la vuelta
     * @param piloto el piloto afectado
     * @param pista la pista en la que corre el piloto
     */
    fun vueltaRapida(piloto: Piloto, pista: Pista){
        if(!piloto.finCarrera && !piloto.dnf){
            if((0..100).random() < probVueltaRapida){
                pista.pista[piloto.fila][piloto.columna] = null
                when(piloto.columna){
                    8 ->{
                        piloto.columna = 0
                        piloto.vueltasCompletadas++
                        piloto.comprobarVueltas()
                        pista.pista[piloto.fila][piloto.columna] = piloto
                        println("${piloto.nombre} ha pintado todos los sectores de morado y lleva ${piloto.vueltasCompletadas} vueltas")
                    }
                    9 ->{
                        piloto.columna = 1
                        piloto.vueltasCompletadas++
                        piloto.comprobarVueltas()
                        pista.pista[piloto.fila][piloto.columna] = piloto
                        println("${piloto.nombre} ha pintado todos los sectores de morado y lleva ${piloto.vueltasCompletadas} vueltas")
                    }
                }
            }
        }
    }
}