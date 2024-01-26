package org.example.models

/**
 * Posibilidad de realizar una vuelta rápida haciendo que el piloto avance 2 posiciones
 * @property probVueltaRapida la probabilidad de realizar una vuelta rápida
 */
interface `Vuelta rápida` {
    val probVueltaRapida: Int

    /**
     * Si el piloto se encuentra corriendo y se cumple la probabilidad de vuelta rápida, el piloto avanzará 2 posiciones
     * cuando esté terminando la vuelta
     * @param pilotos el piloto afectado
     * @param pista la pista en la que corre el piloto
     */
    fun vueltaRapida(pilotos: Pilotos, pista: Pista){
        if(!pilotos.finCarrera && !pilotos.dnf){
            if((0..100).random() < probVueltaRapida){
                pista.pista[pilotos.fila][pilotos.columna] = null
                when(pilotos.columna){
                    8 ->{
                        pilotos.columna = 0
                        pilotos.vueltasCompletadas++
                        pilotos.comprobarVueltas()
                        pista.pista[pilotos.fila][pilotos.columna] = pilotos
                        println("${pilotos.nombre} ha pintado todos los sectores de morado y lleva ${pilotos.vueltasCompletadas} vueltas")
                    }
                    9 ->{
                        pilotos.columna = 1
                        pilotos.vueltasCompletadas++
                        pilotos.comprobarVueltas()
                        pista.pista[pilotos.fila][pilotos.columna] = pilotos
                        println("${pilotos.nombre} ha pintado todos los sectores de morado y lleva ${pilotos.vueltasCompletadas} vueltas")
                    }
                }
            }
        }
    }
}