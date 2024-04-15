package org.example.repositories.personajes

import PersonajeDto
import org.example.repositories.crud.CrudRepository

interface PersonajesRepository : CrudRepository<PersonajeDto, Long> {
    fun findByTipo(tipo: String): List<PersonajeDto>
}