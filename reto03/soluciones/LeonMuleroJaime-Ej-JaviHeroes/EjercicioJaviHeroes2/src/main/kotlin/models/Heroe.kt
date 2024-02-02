package org.example.models
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Clase Heroe
 * @param nombre nombre del objeto alfanumerico
 * @param alias alias del objeto alfanumerico
 * @param altura altura del heroe (>0)
 * @param edad edad del heroe (>0)
 * @param notas notas del heroe (string)
 * @see id id autonumérica
 * @see createAt Fecha y hora de creación del objeto
 * @see updateAt Fecha y hora de actualización del objeto
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
data class Heroe (
    var nombre: String,
    var alias: String,
    var altura: Int,
    var edad: Int,
    var notas: String
) {
    var id: Int = 0
    var createAt: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss a"))
    var updateAt: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss a"))
}