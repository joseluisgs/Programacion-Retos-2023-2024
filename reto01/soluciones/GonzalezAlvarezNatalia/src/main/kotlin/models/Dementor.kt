package org.example.models

/**
 * Clase que representa al enemigo "Dementor" del juego.
 *
 * Hereda de la clase Enemigo.
 *
 * @property daño cantidad de puntos de vida que restará Dementor a la vida de la clase Potter
 */
class Dementor(daño : Int) : Enemigo(daño){
    /**
     * Contador del total de Dementores muertos.
     */
    var total : Int = 0
}