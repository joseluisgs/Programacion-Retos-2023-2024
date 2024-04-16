package org.example.repositories.personaje

import org.example.models.Personaje
import org.example.repositories.crud.CrudRepository
/**
 * Interfaz de Repositorio de Personajes, que implementa el interfaz CRUD, concreta los tipos genéricos y añade
 * operaciones adicionales a implementar
 */
interface PersonajeRepository: CrudRepository<Personaje,Long> {
    fun findByType(type:String): List<Personaje>
}