package org.example.models

/**
 * Fallo de fiabilidad que pueden provocar un DNF en el piloto afectado
 * @property fiabilidad la probabilidad de sufrir dichos fallos
 */
interface Fiabilidad {
    var fiabilidad: Int

    /**
     * Fallo del motor que si se cumple la probabilidad de fiabilidad, el piloto sufrirá un DNF
     * @param pilotos el piloto afectado
     */
    fun falloMotor(pilotos: Pilotos){
        if(!pilotos.dnf && !pilotos.finCarrera){
            val random = (0..100).random()
            if(random < fiabilidad){
                println("🔥🔥🔥")
                println("No puede ser Antonio, ${pilotos.nombre} se queda fuera de la carrera por problemas de fiabilidad")
                pilotos.dnf = true
            }
        }
    }
}