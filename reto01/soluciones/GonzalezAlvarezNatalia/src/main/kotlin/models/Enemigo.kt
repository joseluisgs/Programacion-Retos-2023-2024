package org.example.models

/**
 * Clase que representa a los enemigos del juego.
 *
 * @property daño cantidad de puntos de vida que restará a la vida de la clase Potter
 */
open class Enemigo(val daño : Int = 0) : Personaje() {

    /**
     * Función que resta el daño del enemigo a la vida la clase Potter.
     *
     * @param potter variable que llama a la clase Potter
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun atacar(potter: Potter){
        potter.vida -= daño

        if (potter.vida < 0){
            potter.vida = 0
        }
    }
}