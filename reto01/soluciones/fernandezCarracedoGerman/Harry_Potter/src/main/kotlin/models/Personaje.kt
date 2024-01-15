package org.example.models

/**
 * Clase que representa un Personaje
 * @property nombre Nombre del personaje
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
abstract class Personaje(
    val nombre:String=""
){

    open val icono:String ="😎"

}