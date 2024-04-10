package org.example.repositories.personajes

import org.example.models.Personaje
import org.example.repositories.crud.CrudRepository

interface PersonajesRepository: CrudRepository<Personaje, String> {
    fun findByTipo(tipo: String): List<Personaje>
}