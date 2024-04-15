package org.example.repositories.personaje

import org.example.models.Personaje
import org.example.repositories.crud.CrudRepository

/**
 * Interfaz que define métodos adicionales para el repositorio de personajes.
 * Extiende [CrudRepository] para heredar métodos CRUD básicos.
 */
interface PersonajeRepository : CrudRepository<Personaje, Int> {
    /**
     * Busca todos los personajes de un tipo específico.
     * @param tipo El tipo de personaje a buscar.
     * @return Una lista de personajes del tipo especificado.
     */
    fun findByTipo(tipo: String): List<Personaje>
}