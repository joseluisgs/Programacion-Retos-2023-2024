package org.example.services.personajes

import org.example.models.Personaje

interface PersonajesService {
    fun loadFromCsv(): List<Personaje>
    fun storeToCsv(personajes: List<Personaje>)
    fun loadFromJson(): List<Personaje>
    fun storeToJson(personajes: List<Personaje>)
    fun findAll(): List<Personaje>
    fun findById(id: Int): Personaje
    fun findByTipo(tipo: String): List<Personaje>
    fun save(personaje: Personaje): Personaje
    fun update(id: Int, personaje: Personaje): Personaje
    fun deleteLogico(id: Int): Personaje
    fun deleteFisico(id: Int): Personaje
}