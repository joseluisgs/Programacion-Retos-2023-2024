package org.example.service.personaje

import org.example.models.Personaje

/**
 * Interfaz que define las operaciones disponibles para gestionar personajes.
 */
interface PersonajeService {
    /**
     * Carga personajes desde un archivo CSV.
     * @return Una lista de personajes cargados desde el archivo CSV.
     */
    fun loadFromCsv(): List<Personaje>

    /**
     * Almacena personajes en un archivo CSV.
     * @param personajes La lista de personajes a almacenar en el archivo CSV.
     */
    fun storeToCsv(personajes: List<Personaje>)

    /**
     * Carga personajes desde un archivo JSON.
     * @return Una lista de personajes cargados desde el archivo JSON.
     */
    fun loadFromJson(): List<Personaje>

    /**
     * Almacena personajes en un archivo JSON.
     * @param personajes La lista de personajes a almacenar en el archivo JSON.
     */
    fun storeToJson(personajes: List<Personaje>)

    /**
     * Recupera todos los personajes.
     * @return Una lista de todos los personajes almacenados.
     */
    fun findAll(): List<Personaje>

    /**
     * Recupera un personaje por su ID.
     * @param id El ID del personaje a recuperar.
     * @return El personaje correspondiente al ID especificado.
     */
    fun findById(id: Int): Personaje

    /**
     * Recupera todos los personajes de un tipo específico.
     * @param tipo El tipo de personaje a buscar.
     * @return Una lista de personajes del tipo especificado.
     */
    fun findByTipo(tipo: String): List<Personaje>

    /**
     * Guarda un nuevo personaje o actualiza uno existente.
     * @param personaje El personaje a guardar o actualizar.
     * @return El personaje guardado o actualizado.
     */
    fun save(personaje: Personaje): Personaje

    /**
     * Actualiza un personaje existente.
     * @param id El ID del personaje a actualizar.
     * @param personaje El nuevo estado del personaje.
     * @return El personaje actualizado.
     */
    fun update(id: Int, personaje: Personaje): Personaje

    fun delete(id: Int, logical: Boolean = false): Personaje
}