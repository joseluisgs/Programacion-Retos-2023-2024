package org.example.models

/**
 * Clase que representa al superhéroe.
 * @property id Identificador único del superhéroe.
 * @property nombre Nombre real del superhéroe.
 * @property alias Alias o nombre en clave del superhéroe.
 * @property altura Altura del superhéroe en centímetros.
 * @property edad Edad del superhéroe en años.
 * @property notas Información adicional sobre el superhéroe.
 * @property createdAt Fecha y hora de creación del registro del superhéroe.
 * @property updatedAt Fecha y hora de la última actualización del registro del superhéroe.
 */
data class Superheroe(
    var id: Int = 0,
    var nombre: String,
    var alias: String,
    var altura: Int,
    var edad: Int,
    var notas: String,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis()
){
    override fun toString(): String {
        return "Superheroe(id=$id, nombre='$nombre', alias='$alias', altura='$altura, edad='$edad, notas='$notas', createdAt='$createdAt', updatedAt='$updatedAt')"
    }
}