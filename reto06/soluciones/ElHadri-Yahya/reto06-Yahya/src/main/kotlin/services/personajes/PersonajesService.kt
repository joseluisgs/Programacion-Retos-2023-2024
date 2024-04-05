package services.personajes

import models.Personaje
import java.io.File

interface PersonajesService {
    fun loadFromCsv(file:String): List<Personaje>
    fun storeToCsv(personajes: List<Personaje>)
    fun loadFromJson(file:String): List<Personaje>
    fun storeToJson(personajes: List<Personaje>)
    fun findAll(): List<Personaje>
    fun findByName(name: String): Personaje
    fun save(personaje: Personaje): Personaje
    fun update(name: String, personaje: Personaje): Personaje
    fun delete(name: String, logical: Boolean = false): Personaje
}