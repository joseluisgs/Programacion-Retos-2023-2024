package org.example.services.personajes

import org.example.exceptions.personajes.PersonajeException
import org.example.models.Personaje
import org.example.repositories.Personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging

private val logger = logging()
class PersonajeServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personajesRepository: PersonajesRepository,
    private val personajesCache: PersonajesCache
): PersonajesService {
    override fun loadFromCsv(): List<Personaje> {
        logger.debug { "Cargando personajes desde CSV" }
        return storageCsv.load("personajes.csv")
    }

    override fun storeFromCsv(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en CSV" }
        storageCsv.store(personajes)
    }

    override fun loadFromJsom(): List<Personaje> {
        logger.debug { "Cargando personajes desde JSON" }
        return storageJson.load("personajes-back.json")
    }

    override fun storeFromJsom(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en JSON" }
        storageJson.store(personajes)
    }

    override fun findAll(): List<Personaje> {
        logger.debug { "Buscando todos los personajes " }
        return personajesRepository.findAll()
    }

    override fun getByName(name: String): Personaje {
        logger.debug { "Buscando personaje por nombre: $name" }
        return personajesCache.get(name) ?: personajesRepository.getByName(name)?.also {
            personajesCache.put(name, it)
        }?: throw PersonajeException.PersonajeNotFoundException("Personaje no encontrado con nombre $name")
    }

    override fun update(name: String, item: Personaje): Personaje {
        logger.debug { "Actualizando personaje con nombre: $name" }
        return personajesRepository.update(name, item).also {
            personajesCache.put(name, it!!)
        } ?: throw PersonajeException.PersonajeNotFoundException("Personaje no encontrado con nombre $name")
    }

    override fun save(item: Personaje): Personaje {
        logger.debug { "Guardando personaje $item" }
        return personajesRepository.save(item).also {
            personajesCache.put(item.nombre, item)
        }
    }

    override fun delete(name: String, logical: Boolean): Personaje {
        logger.debug { "Borrando personaje con nombre $name" }
        return personajesRepository.delete(name, logical).also {
            personajesCache.remove(name)
        }?: throw PersonajeException.PersonajeNotFoundException("Persona no encontrada con nombre $name")
    }
}