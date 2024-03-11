package org.example.services

/**
 * Interfaz que define las operaciones del backup.
 *
 * @author Natalia González Álvarez
 * @since 1.0
 */
interface Backup {
    /**
     * Realiza la operación del backup.
     */
    fun backup()

    /**
     * Restaura los datos desde el backup.
     */
    fun restore()
}
