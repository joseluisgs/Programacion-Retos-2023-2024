package models

data class Cliente(
    val id: Int,
    var mesa:Int,
    val menuElegido:Menu
)