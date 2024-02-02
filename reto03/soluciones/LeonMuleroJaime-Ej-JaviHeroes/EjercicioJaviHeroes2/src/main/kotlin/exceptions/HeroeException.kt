package org.example.exceptions

/**
 * Clase sellada donde almacenamos las clases para cada excepción
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see NotFound
 * @see NombreNotValid
 * @see AliasNotValid
 * @see AlturaNotValid
 * @see EdadNotValid
 */
sealed class HeroeException(message: String): Exception(message) {
    class NotFound(id: Int): HeroeException("No se ha encontrado ningún héroe con la id $id")
    class NombreNotValid(message: String): HeroeException(message)
    class AliasNotValid(message: String): HeroeException(message)
    class AlturaNotValid(message: String): HeroeException(message)
    class EdadNotValid(message: String): HeroeException(message)
}