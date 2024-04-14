package org.example.services.personajes

import org.example.models.Personaje

/**
 * Interfaz que define las operaciones disponibles para gestionar personajes.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface PersonajeService {
    /**
     * Carga los personajes desde un archivo CSV.
     *
     * @return Lista de personajes cargados desde el archivo CSV.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun loadFromCsv(): List<Personaje>

    /**
     * Almacena los personajes en un archivo CSV.
     *
     * @param personajes Lista de personajes a almacenar.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun storeToCsv(personajes: List<Personaje>)

    /**
     * Carga los personajes desde un archivo JSON.
     *
     * @return Lista de personajes cargados desde el archivo JSON.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun loadFromJson(): List<Personaje>

    /**
     * Almacena los personajes en un archivo JSON.
     *
     * @param personajes Lista de personajes a almacenar.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun storeToJson(personajes: List<Personaje>)

    /**
     * Busca y devuelve todos los personajes.
     *
     * @return Lista de todos los personajes.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findAll(): List<Personaje>

    /**
     * Busca y devuelve los personajes del tipo especificado.
     *
     * @param tipo Tipo de personaje a buscar.
     * @return Lista de personajes del tipo especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findByTipo(tipo: String): List<Personaje>

    /**
     * Busca y devuelve un personaje por su identificador.
     *
     * @param id Identificador del personaje a buscar.
     * @return El personaje encontrado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findById(id: Long): Personaje

    /**
     * Guarda un nuevo personaje.
     *
     * @param personaje El personaje a guardar.
     * @return El personaje guardado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun save(personaje: Personaje): Personaje

    /**
     * Actualiza un personaje existente.
     *
     * @param id Identificador del personaje a actualizar.
     * @param personaje Datos actualizados del personaje.
     * @return El personaje actualizado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun update(id: Long, personaje: Personaje): Personaje

    /**
     * Elimina un personaje por su identificador.
     *
     * @param id Identificador del personaje a eliminar.
     * @param logical Indica si se debe realizar un borrado lógico (marcar como eliminado) o físico.
     * @return El personaje eliminado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun delete(id: Long, logical: Boolean = false): Personaje
}
