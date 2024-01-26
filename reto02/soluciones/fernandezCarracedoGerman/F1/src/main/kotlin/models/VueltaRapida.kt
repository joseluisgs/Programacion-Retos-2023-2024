package org.example.models
import org.lighthousegames.logging.logging
private val log = logging()
/**
 * Interfaz que implementa el método Vuelta Rápida
 * @see Piloto
 * @see Equipo
 */
interface VueltaRapida {
    /**
     * Hace que el piloto avance 2 posiciones. Se suman al avance actual del piloto
     * @param piloto Piloto que logra la vuelta rápida
     */
    fun vueltaRapida(piloto:Piloto) {
        piloto.avance = 2
        piloto.estado = EstadoPiloto.VUELTA_RAPIDA
        piloto.icono = "🟣"
        log.debug { "🟣 ¡¡¡VUELTA RÁPIDA!!! ¡¡${piloto.nombre} hace vuelta rápida !! 🟣" }

    }
}