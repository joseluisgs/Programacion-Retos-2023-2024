package org.example.models
/**

Clase de datos que representa a un personaje en el sistema.
@property id Identificador único del personaje.
@property nickName Apodo o alias del personaje.
@property nombre Nombre del personaje.
@property edad Edad del personaje.
@property vivo Booleano que indica si el personaje está vivo (true) o fallecido (false).
@property villano Booleano que indica si el personaje es un villano (true) o no (false).
@property habilidad Habilidad especial o destreza del personaje.
@property puntosCombate Puntos de combate que indican la fuerza del personaje.
 */
/*import kotlinx.serialization.Serializable

@Serializable*/
data class Personaje(
    val id: Int,
    val nickName: String,
    val nombre: String,
    val edad: Int,
    val vivo: Boolean,
    val villano: Boolean,
    val habilidad: String,
    val puntosCombate: Int
)