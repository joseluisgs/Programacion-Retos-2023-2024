package org.example.services.personajes

import org.example.exceptions.personajes.PersonajeExceptions
import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.storage.Storage
import org.example.validators.PersonajeValidator
import org.lighthousegames.logging.logging

private val logger = logging()

class PersonajesServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personasRepository: PersonajesRepository,
    private val personasCache: PersonajesCache,
    private val validator: PersonajeValidator
): PersonajesService {
    override fun loadFromCsv(): List<Personaje> {
        logger.debug { "Cargando personajes desde CSV" }
        return storageCsv.load("personajes.csv")
    }

    override fun storeToCsv(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en CSV" }
        personajes.forEach { validator.validate(it) }
        storageCsv.store(personajes)
    }

    override fun loadFromJson(): List<Personaje> {
        logger.debug { "Cargando personajes desde JSON" }
        return storageJson.load("personajes-back.json")
    }

    override fun storeToJson(personajes: List<Personaje>) {
        logger.debug { "Guardando personajes en JSON" }
        personajes.forEach { validator.validate(it) }
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
        validator.validate(personaje)
        return personasRepository.save(personaje).also {
            personasCache.put(personaje.id, it)
        }
    }

    override fun update(id: Int, personaje: Personaje): Personaje {
        logger.debug { "Actualizando personaje con id $id" }
        validator.validate(personaje)
        return personasRepository.update(id, personaje).also {
            personasCache.put(id, it!!)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje con id $id no encontrado")
    }

    override fun deleteLogico(id: Int): Personaje {
        logger.debug { "Realizando borrado lógico de personaje con id $id" }
        return personasRepository.deleteLogico(id).also {
            personasCache.remove(id)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje con id $id no encontrado")
    }

    override fun deleteFisico(id: Int): Personaje {
        logger.debug { "Realizando borrado físico de personaje con id $id" }
        return personasRepository.deleteFisico(id).also {
            personasCache.remove(id)
        } ?: throw PersonajeExceptions.PersonajeNotFoundException("Personaje con id $id no encontrado")
    }
}