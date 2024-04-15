package org.example.repositories.personajes

import org.example.models.Personaje
import org.example.repositories.crud.CrudRepository

/**
 * Interfaz que define las operaciones específicas para el repositorio de personajes.
 *
 * Extiende la interfaz [CrudRepository] para heredar las operaciones básicas de CRUD.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface PersonajesRepository : CrudRepository<Personaje, Long> {
    /**
     * Busca y devuelve todos los personajes de un tipo específico.
     *
     * @param tipo Tipo de personaje a buscar.
     * @return Lista de personajes del tipo especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findByTipo(tipo: String): List<Personaje>
}
