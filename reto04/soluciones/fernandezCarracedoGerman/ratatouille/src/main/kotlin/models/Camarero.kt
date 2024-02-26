package org.example.models

import org.lighthousegames.logging.logging

class Camarero(
    id:Int = 0,
    nombre:String = "",
    val numMesa:Int = 0
):Empleado(id,nombre) {

    var comandaActual:Comanda? = null

    var estado: Estado = Estado.ESPERANDO_CLIENTE
    var direccion: Direccion = Direccion.SALON

    val icono = listOf("🤵🏻‍♂️", "🤵🏻‍♀️","🤵🏼‍♀️","🤵🏽‍♂️","🤵🏿", "🤵🏼").random()

    enum class Estado {ESPERANDO_CLIENTE, BUSCANDO_CLIENTE, LLEVANDO_COMANDA_COCINA, ESPERANDO_COMANDA_PREPARADA, LLEVANDO_COMANDA_MESA}//, EN_EL_SUELO }
    enum class Direccion { SALON, COCINA }

    fun cambioDireccion(){
        direccion = if (direccion == Direccion.SALON)
            Direccion.COCINA
        else
            Direccion.SALON
    }


}