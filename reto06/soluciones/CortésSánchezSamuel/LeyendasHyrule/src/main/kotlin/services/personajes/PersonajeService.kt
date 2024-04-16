package services.personajes

import models.Personaje

interface PersonajeService {
    fun loadFromCsv(file: String ): List<Personaje>
    fun storeToCsv(personas: List<Personaje>)
    fun loadFromJson(file : String): List<Personaje>
    fun storeToJson(personas: List<Personaje>)
    fun findAll(): List<Personaje>
    fun findByName(name : String): Personaje
    fun save(persona: Personaje): Personaje
    fun update(id: String, persona: Personaje): Personaje
    fun delete(id: String, logical: Boolean = false): Personaje
}