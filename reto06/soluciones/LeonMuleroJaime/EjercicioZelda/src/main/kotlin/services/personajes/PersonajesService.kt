package org.example.services.personajes

import org.example.models.Personaje

interface PersonajesService {
    fun loadFromCsv(): List<Personaje>
    fun storeToCsv(personajes: List<Personaje>)
    fun loadFromJson(): List<Personaje>
    fun storeToJson(personajes: List<Personaje>)
    fun findAll(): List<Personaje>
    fun findByName(name: String): Personaje
    fun findByTipo(tipo: String): List<Personaje>
    fun save(personaje: Personaje): Personaje
    fun update(name: String, personaje: Personaje): Personaje
    fun deleteLogico(name: String): Personaje
    fun deleteFisico(name: String): Personaje
}