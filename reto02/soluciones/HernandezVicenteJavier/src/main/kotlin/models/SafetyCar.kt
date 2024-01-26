package org.example.models

/**
 * Puede provocar la salida de un safety car haciendo que el piloto pierda posiciones
 */
interface SafetyCar {
    /**
     * Si el piloto se encuentra corriendo, y se cumple la probabilidad de fallo, este provocará la salida
     * del safety car haciéndole perder 1 posición
     * @param pilotos el piloto afectado
     * @param pista la pista en la que se encuentra el piloto
     */
    fun provocarSafetyCar(pilotos: Pilotos, pista: Pista){
        var nuevaColumna = pilotos.columna
        if(!pilotos.finCarrera && !pilotos.dnf){
            if((0..100).random() < pilotos.probFallo){
                pista.pista[pilotos.fila][pilotos.columna] = null
                println("🚔🚨🚔")
                println("SAFETY CAR , SAFETY CAR, Vaya error por parte de ${pilotos.nombre}")
                when(pilotos.columna){
                    0 ->{
                        nuevaColumna = 9
                        pilotos.columna = nuevaColumna
                        pilotos.vueltasCompletadas--
                        pista.pista[pilotos.fila][pilotos.columna] = pilotos
                    }
                    else ->{
                        nuevaColumna--
                        pilotos.columna = nuevaColumna
                        pista.pista[pilotos.fila][pilotos.columna] = pilotos
                    }
                }
            }
        }
    }
}