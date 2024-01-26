package org.example.models.acciones

import org.example.models.base.Piloto
import org.example.controllers.Pista

/**
 * Puede provocar la salida de un safety car haciendo que el piloto pierda posiciones
 */
interface SafetyCar {
    /**
     * Si el piloto se encuentra corriendo, y se cumple la probabilidad de fallo, este provocará la salida
     * del safety car haciéndole perder 1 posición
     * @param piloto el piloto afectado
     * @param pista la pista en la que se encuentra el piloto
     */
    fun provocarSafetyCar(piloto: Piloto, pista: Pista){
        var nuevaColumna = piloto.columna
        if(!piloto.finCarrera && !piloto.dnf){
            if((0..100).random() < piloto.probFallo){
                pista.pista[piloto.fila][piloto.columna] = null
                println("🚔🚨🚔")
                println("SAFETY CAR , SAFETY CAR, Vaya error por parte de ${piloto.nombre}")
                when(piloto.columna){
                    0 ->{
                        nuevaColumna = 9
                        piloto.columna = nuevaColumna
                        piloto.vueltasCompletadas--
                        pista.pista[piloto.fila][piloto.columna] = piloto
                    }
                    else ->{
                        nuevaColumna--
                        piloto.columna = nuevaColumna
                        pista.pista[piloto.fila][piloto.columna] = piloto
                    }
                }
            }
        }
    }
}