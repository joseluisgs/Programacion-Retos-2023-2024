package org.example.services.personaje

import org.example.models.Personaje

interface PersonajesService {

    // CRUD
    fun findAll():List<Personaje>
    fun findById(id:Long): Personaje
    fun save(item:Personaje): Personaje
    fun update(id: Long, item: Personaje):Personaje
    fun delete(id:Long, logical: Boolean = false):Personaje

    // Adicionales repository
    fun findByType(type:String): List<Personaje>

    // Storage
    fun storeToCsv(data:List<Personaje>, fileName: String): Boolean
    fun storeToJson(data:List<Personaje>, fileName: String): Boolean
    fun loadFromCsv(fileName:String):List<Personaje>
    fun loadFromJson(fileName:String):List<Personaje>
}