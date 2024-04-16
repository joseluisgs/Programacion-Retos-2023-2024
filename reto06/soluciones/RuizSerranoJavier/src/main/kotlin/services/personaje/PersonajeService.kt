package org.example.services.personaje

import org.example.models.Personaje

interface PersonajeService {
    fun loadFromCsv(): List<Personaje>
    fun storeToCsv(personas: List<Personaje>)
    fun loadFromJson(): List<Personaje>
    fun storeToJson(personas: List<Personaje>)
    fun findAll(): List<Personaje>
    fun findByTipo(tipo: String): List<Personaje>
}