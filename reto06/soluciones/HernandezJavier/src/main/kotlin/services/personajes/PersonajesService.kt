package org.example.services.personajes

import com.github.michaelbull.result.Result
import org.example.exceptions.personajes.PersonajeError
import org.example.exceptions.storage.StorageError
import org.example.models.Personaje

interface PersonajesService {
    fun loadFromCsv(): Result<List<Personaje>, StorageError>
    fun storeFromCsv(personajes: List<Personaje>): Result<Unit, StorageError>
    fun loadFromJsom(): Result<List<Personaje>, StorageError>
    fun storeFromJsom(personajes: List<Personaje>): Result<Unit, StorageError>
    fun findAll(): Result<List<Personaje>, PersonajeError>
    fun getByName(name: String): Result<Personaje, PersonajeError>
    fun update(name: String, item: Personaje): Result<Personaje, PersonajeError>
    fun save(item: Personaje):Result<Personaje, PersonajeError>
    fun delete(name: String): Result<Personaje, PersonajeError>
}