package org.example.models.acciones

import org.example.models.base.Piloto

/**
 * Fallo de fiabilidad que pueden provocar un DNF en el piloto afectado
 * @property fiabilidad la probabilidad de sufrir dichos fallos
 */
interface Fiabilidad {
    var fiabilidad: Int

    /**
     * Fallo del motor que si se cumple la probabilidad de fiabilidad, el piloto sufrirá un DNF
     * @param piloto el piloto afectado
     */
    fun falloMotor(piloto: Piloto){
        if(!piloto.dnf && !piloto.finCarrera){
            val random = (0..100).random()
            if(random < fiabilidad){
                println("🔥🔥🔥")
                println("No puede ser Antonio, ${piloto.nombre} se queda fuera de la carrera por problemas de fiabilidad")
                piloto.dnf = true
            }
        }
    }
}