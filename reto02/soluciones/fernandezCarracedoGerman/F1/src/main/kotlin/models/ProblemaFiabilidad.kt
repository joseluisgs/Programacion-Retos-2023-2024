package org.example.models
import org.lighthousegames.logging.logging
private val log = logging()
/**
 * Interfaz que implementa el método Problema Fiabilidad
 * @see Piloto
 * @see Equipo
 */
interface ProblemaFiabilidad {
    /**
     * Hace que el piloto quede descalificado al ocurrir un fallo mecánico en su coche
     * @param piloto Piloto que sufre el problema de fiabilidad del equipo
     */
    fun problemaFiabilidad(piloto: Piloto){
        piloto.estado = EstadoPiloto.DNF
        piloto.icono = "💥"
        piloto.tiempoCarrera+= 99999999 // para ordenaciones posteriores de los pilotos por tiempos
        log.debug { "💥 ¡¡¡PROBLEMA DE FIABILIDAD!!! ¡¡El coche de ${piloto.nombre} no puede mas!! 💥" }

    }
}