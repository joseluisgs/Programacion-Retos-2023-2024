package org.example.models


/**
 * Clase abstracta para la implementacion de la herencia en enemigo
 * @see Ministerio
 * @author Javier Ruiz
 * @since 1.0.0
 */
abstract class Enemigo: Personaje() {

    /**
     * Funcion que usaran los enemigos para atacar
     * @author Javier Ruiz
     * @since 1.0.0
     */
    abstract fun atacar() :Boolean
}
