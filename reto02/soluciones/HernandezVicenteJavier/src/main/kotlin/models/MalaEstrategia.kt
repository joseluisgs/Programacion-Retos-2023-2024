package org.example.models

/**
 * Mala estrategía que hará parar a los pilotos durante un periodo de tiempo
 * @property tiempoPerdido el tiempo que perderan
 */
interface MalaEstrategia {
    var tiempoPerdido: Int

    /**
     * Si el piloto se encuentra en pista corriendo, si se cumple la probabilidad de fallo,
     * estos sufriran una perdida de tiempo de 2 segundos
     * @param pilotos el piloto
     */
    fun cagada(pilotos: Pilotos){
        if(!pilotos.finCarrera && !pilotos.dnf){
            if((0..100).random() < pilotos.probFallo){
                println("Ese undercut no ha sido de manual y hace perder tiempo a ${pilotos.nombre}, Antonio")
                tiempoPerdido = 2
            }
        }
    }
}