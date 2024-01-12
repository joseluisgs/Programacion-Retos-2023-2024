package models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del personaje/amigo
 * @property danio que inflingen
 */
abstract class Enemigos(override val nombre: String = "", val danio: Int): Personajes(nombre)