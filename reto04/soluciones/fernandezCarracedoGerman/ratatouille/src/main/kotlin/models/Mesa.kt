package org.example.models

/**
 * Clase Mesa
 */
data class Mesa(
    val numeroMesa:Int
) {
    val icono = "🟫"
    var ocupada:Boolean = false
}