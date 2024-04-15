package org.example.service.personaje

import org.example.exceptions.personaje.PersonajeExceptions
import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepository
import org.example.service.cache.personaje.PersonajeCache
import org.example.service.storage.Storage
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * Implementación de [PersonajeService] que proporciona métodos para gestionar los personajes.
 * @property storageCsv Almacenamiento para operaciones relacionadas con archivos CSV.
 * @property storageJson Almacenamiento para operaciones relacionadas con archivos JSON.
 * @property personajeRepository Repositorio para acceder a los datos de los personajes.
 * @property personajeCache Caché para mejorar el rendimiento al acceder a los datos de los personajes.
 */
class PersonajeServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personasRepository: PersonajeRepository,
    private val personasCache: PersonajeCache,

): PersonajeService {
    override fun loadFromCsv(): List<Personaje> {
        logger.debug { "Cargando personajes desde CSV" }
        return storageCsv.load("personajes.csv")
    }

    override fun storeToCsv(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en CSV" }
        storageCsv.store(personajes)
    }

    override fun loadFromJson(): List<Personaje> {
        logger.debug { "Cargando personajes desde JSON" }
        return storageJson.load("personajes-back.json")
    }

    override fun storeToJson(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en JSON" }
        storageJson.store(personajes)
    }

    override fun findAll(): List<Personaje> {
        logger.debug { "Buscando todos los personajes" }
        return personasRepository.findAll()
    }

    override fun findById(id: Int): Personaje {
        logger.debug { "Buscando personaje con id $id" }
        return personasCache.get(id) ?: personasRepository.findById(id)?.also {
            personasCache.put(id, it)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje con id $id no encontrado")
    }

    override fun findByTipo(tipo: String): List<Personaje> {
        logger.debug { "Buscando personajes por tipo $tipo" }
        return personasRepository.findByTipo(tipo)
    }

    override fun save(personaje: Personaje): Personaje {
        logger.debug { "Guardando personaje $personaje" }
        return personasRepository.save(personaje).also {
            personasCache.put(personaje.id, it)
        }
    }

    override fun update(id: Int, personaje: Personaje): Personaje {
        logger.debug { "Actualizando personaje con id $id" }
        return personasRepository.update(id, personaje).also {
            personasCache.put(id, it!!)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje con id $id no encontrado")
    }
    override fun delete(id: Int, logical: Boolean): Personaje {
        logger.debug { "Borrando personaje con id $id" }
        return personasRepository.delete(id).also {
            personasCache.remove(id)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Persona no encontrada con id $id")
    }

}