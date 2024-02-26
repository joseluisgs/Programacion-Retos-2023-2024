package models

import kotlin.random.Random

data class Menu(
    val primerPLato: String,
    val segundoPlato: String,
    val postre: String,
    val precio: Double,
    val valoracion: Int
) {
    companion object {
        private val menus = listOf(
            Menu("Sopa castellana", "Ratatouille", "Tarta de queso", 15.0, 5),
            Menu("Ensaladilla rusa", "Solomillo", "Mousse de chocolate", 20.0, 4),
            Menu("Pasta carbonara", "Pollo al horno", "Tiramisú", 18.0, 3),
            Menu("Ensalada César", "Risotto", "Pudding", 22.0, 4),
            Menu("Menestra de verduras", "Salmón ahumado", "Helado de vainilla", 25.0, 5)
        )

        fun menuAleatorio(): Menu {
            return menus.random()
        }
    }
}
