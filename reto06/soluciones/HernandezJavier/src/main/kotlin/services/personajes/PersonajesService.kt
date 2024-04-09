package org.example.services.personajes

import org.example.models.Personaje

interface PersonajesService {
    fun loadFromCsv():List<Personaje>
    fun storeFromCsv(personajes: List<Personaje>)
    fun loadFromJsom():List<Personaje>
    fun storeFromJsom(personajes: List<Personaje>)
    fun findAll(): List<Personaje>
    fun getByName(name: String): Personaje
    fun update(name: String, item: Personaje): Personaje
    fun save(item: Personaje):Personaje
    fun delete(name: String, logical: Boolean = false): Personaje
}