package org.example.models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * Clase que representa un menú con opciones para el primer plato, segundo plato y postre.
 *
 * @property primerPlato Lista de opciones para el primer plato.
 * @property segundoPlato Lista de opciones para el segundo plato.
 * @property postre Lista de opciones para el postre.
 */
data class Menu(
    val primerPlato: List<String> = listOf(
        "Sopa de Cebolla Gratinada",
        "Ratatouille",
        "Quiche Lorraine",
        "Salade Niçoise",
        "Bouillabaisse",
    ),
    val segundoPlato: List<String> = listOf(
        "Coq au Vin",
        "Boeuf Bourguignon",
        "Magret de Pato con Salsa de Naranja",
        "Cassoulet",
        "Sole Meunière"
    ),
    val postre: List<String> = listOf(
        "Crème Brûlée",
        "Tarte Tatin",
        "Mousse de Chocolate",
        "Profiteroles con Salsa de Chocolate",
        "Soufflé de Frambuesa"
    )
)
