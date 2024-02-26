package org.example.models

import org.example.exceptions.RestauranteExceptions
import org.lighthousegames.logging.logging

private const val TIEMPO_COCINA_COMANDA = 3 // Tiempo que se tarda en preparar la comanda actual

/**
 * Clase Cocinero
 */
class Cocinero(
    id:Int = 0,
    nombre:String = "Remy"
):Empleado(id,nombre) {

    private val log = logging()

    var comandaActual:Comanda? = null
    var estado:Estado = Estado.ESPERANDO_NUEVA_COMANDA

    val icono = "🧑🏿‍🍳"

    enum class Estado { ESPERANDO_NUEVA_COMANDA, COCINANDO }

    /**
     * Restamos un segundo al tiempo de cocinado, y cuando este llega a 0, la comanda está lista
     */
    fun cocinar(){
        if (comandaActual != null) {
            comandaActual!!.tiempoRestanteCocinar--
        } else {
            log.error { "Cocinero.cocinar: Se intentó cocinar una comanda inexistente" }
            throw RestauranteExceptions.ComandaException("Comanda inexistente")
        }
    }

}