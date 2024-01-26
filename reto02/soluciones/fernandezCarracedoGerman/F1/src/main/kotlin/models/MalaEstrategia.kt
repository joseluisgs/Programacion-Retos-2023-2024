package org.example.models
import org.lighthousegames.logging.logging
private val log = logging()
/**
 * Interfaz que implementa el método Mala Estrategia
 * @see Piloto
 * @see Equipo
 */
interface MalaEstrategia {
    /**
     * Hace que el piloto esté 2 segundos parado. Se suman a los segundos parados que ya tuviera por otro incidente
     * @param piloto Piloto que sufre este evento
     */
    fun malaEstrategia(piloto:Piloto) {
        piloto.segundosStop += 2
        piloto.estado = EstadoPiloto.MALA_ESTRATEGIA
        piloto.icono = "💩"
        log.debug { "💩 ¡¡¡MALA ESTRATEGIA!!! ¡¡${piloto.equipo.nombre} ha tomado una mala decisión con ${piloto.nombre} y pierde 2 segundos!! 💩" }
    }
}