package org.example.models

/**
 * Clase que representa un menú en el restaurante.
 *
 * @property menu1 Lista de platos que conforman el menú 1.
 * @property menu2 Lista de platos que conforman el menú 2.
 * @property menu3 Lista de platos que conforman el menú 3.
 * @property menu4 Lista de platos que conforman el menú 4.
 * @property menu5 Lista de platos que conforman el menú 5.
 * @property menus Lista de menús disponibles en el restaurante.
 */
data class Menu(
    val menu1: List<Plato> = listOf(
        Plato("Menú 1", "Escargots à l'ail", "Boeuf bourguignon", "Crème brûlée", 25.50, 4.5)
    ),
    val menu2: List<Plato> = listOf(
        Plato("Menú 2", "Soupe à l'oignon", "Coq au vin", "Tarte Tatin", 23.75, 4.2)
    ),
    val menu3: List<Plato> = listOf(
        Plato("Menú 3", "Quiche Lorraine", "Magret de canard", "Mousse au chocolat", 19.90, 4.8)
    ),
    val menu4: List<Plato> = listOf(
        Plato("Menú 4", "Salade Niçoise", "Ratatouille", "Profiteroles", 17.25, 4.0)
    ),
    val menu5: List<Plato> = listOf(
        Plato("Menú 5", "Croque-monsieur", "Poulet rôti", "Crêpe suzette", 14.95, 4.3)
    ),
    val menus: List<List<Plato>> = listOf(menu1, menu2, menu3, menu4, menu5)
)