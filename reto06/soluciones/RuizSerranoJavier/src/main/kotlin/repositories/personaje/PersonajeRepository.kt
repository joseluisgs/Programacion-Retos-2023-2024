package org.example.repositories.personaje

import org.example.models.Personaje
import org.example.repositories.base.CrudRepository

interface PersonajeRepository: CrudRepository<Personaje, Long> {
    fun findByTipo(tipo: String): List<Personaje>

}