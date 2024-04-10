package org.example.services.personajes

import org.example.exceptions.personajes.PersonajeExceptions
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging

private val logger = logging()

class PersonajesServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personasRepository: PersonajesRepository,
    private val personasCache: PersonajesCache
): PersonajesService {
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

    override fun findByName(name: String): Personaje {
        logger.debug { "Buscando personaje con nombre $name" }
        return personasCache.get(name) ?: personasRepository.findByName(name)?.also {
            personasCache.put(name, it)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje llamado $name no encontrado")
    }

    override fun findByTipo(tipo: String): List<Personaje> {
        logger.debug { "Buscando personajes por tipo $tipo" }
        return personasRepository.findByTipo(tipo)
    }

    override fun save(personaje: Personaje): Personaje {
        logger.debug { "Guardando personaje $personaje" }
        return personasRepository.save(personaje).also {
            personasCache.put(personaje.nombre, it)
        }
    }

    override fun update(name: String, personaje: Personaje): Personaje {
        logger.debug { "Actualizando personaje llamado $name" }
        return personasRepository.update(name, personaje).also {
            personasCache.put(name, it!!)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje llamado $name no encontrado")
    }

    override fun deleteLogico(name: String): Personaje {
        logger.debug { "Realizando borrado lógico de personaje llamado $name" }
        return personasRepository.deleteLogico(name).also {
            personasCache.remove(name)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje llamado $name no encontrado")
    }

    override fun deleteFisico(name: String): Personaje {
        logger.debug { "Realizando borrado físico de personaje llamado $name" }
        return personasRepository.deleteFisico(name).also {
            personasCache.remove(name)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje llamado $name no encontrado")
    }
}