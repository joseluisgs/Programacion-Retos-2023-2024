package org.example.models.personajes

class Mesa(
    val id: Int,
    var cliente: Boolean = false,
    var plato: String = "",
    var clienteComiendo: Int = 0
) {
    fun comprobarMesaLibre(mesa: Mesa): Boolean {
        if (!mesa.cliente) return true
        else return false
    }
}