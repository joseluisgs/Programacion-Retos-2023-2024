package org.example.models.personajes

import org.example.controllers.Ratatouille
import org.example.models.Posicion

class Camarero(
    val id: Int,
    var plato: String = "",
    var direccion: String = "N",
    var clienteEnMesa: Boolean = false,
) {
    fun moverCamarero(camarero: Camarero) {
        val posicionCamarero = getPosicionCamarero(camarero)
        if (camarero.direccion == "N") {
            Ratatouille.restaurante[posicionCamarero.fila - 1][posicionCamarero.col].persona = camarero
            Ratatouille.restaurante[posicionCamarero.fila][posicionCamarero.col].persona = null
        } else {
            Ratatouille.restaurante[posicionCamarero.fila + 1][posicionCamarero.col].persona = camarero
            Ratatouille.restaurante[posicionCamarero.fila][posicionCamarero.col].persona = null
        }
    }

    private fun getPosicionCamarero(camarero: Camarero): Posicion {
        var posicion = Posicion(0, 0)
        for (fil in Ratatouille.restaurante.indices) {
            for (col in Ratatouille.restaurante[fil].indices) {
                if (Ratatouille.restaurante[fil][col].persona == camarero) {
                    posicion = Posicion(fil, col)
                }
            }
        }
        return posicion
    }

    fun direccionCamarero(camarero: Camarero) {
        val posicionCamarero = getPosicionCamarero(camarero)
        if (camarero.direccion == "N" && posicionCamarero.fila == 1) camarero.direccion = "S"
        if (camarero.direccion == "S" && posicionCamarero.fila == 5) camarero.direccion = "N"
    }

    fun comprobarMesaConCliente(mesa: Mesa, camarero: Camarero) {
        if (mesa.cliente) {
            clienteEnMesa = true
        }
    }
}