package org.example.services.personaje

import org.example.cache.Cache
import org.example.config.Config
import org.example.exceptions.personajes.PersonajesException
import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepository
import org.example.services.storage.base.Storage
import org.example.validators.personaje.PersonajesValidator
import org.lighthousegames.logging.logging

/**
 * Clase con el servicio que hará de coordinador de las peticiones y entregas de información
 * a los distintos componentes de la aplicación
 */

private val log = logging()


class PersonajesServiceImpl(
    private val repository: PersonajeRepository,
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val cache: Cache<Personaje,Long>,
    private val validator: PersonajesValidator
) : PersonajesService{

    override fun findAll(): List<Personaje> {
        log.debug { "Recuperando todos los personajes" }
        return repository.findAll()
    }

    override fun findById(id: Long): Personaje {
        log.debug { "Buscando personaje por id $id" }
        return cache.get(id)?: repository.findById(id)?.also {
            cache.put(id,it)
        }?: throw PersonajesException.PersonajeNotFound("Personaje no encontrado con id $id")
    }

    override fun save(item: Personaje): Personaje {
        log.debug { "Guardando personaje $item" }

        log.debug { "Validando personaje" }
        validator.validate(item)

        return repository.save(item)
            .also { cache.put(item.id,it) }

    }

    override fun update(id: Long, item: Personaje): Personaje {
        log.debug { "Actualizando personaje id $id" }

        log.debug { "Validando personaje" }
        validator.validate(item)

        return repository.update(id, item).also {
            cache.put(id, it!!)
        } ?: throw PersonajesException.PersonajeNotUpdatedException("Personaje no se pudo modificar, id $id")
    }

    override fun delete(id: Long, logical:Boolean): Personaje {
        log.debug { "Eliminando personaje id $id" }
        return repository.delete(id, logical).also {
            cache.remove(id)
        } ?: throw PersonajesException.PersonajeNotDeletedException("Personaje no se pudo eliminar, id $id")
    }

    override fun findByType(type: String): List<Personaje> {
        log.debug { "Buscando personajes por tipo $type" }
        return repository.findByType(type)
    }

    override fun storeToCsv(data: List<Personaje>, fileName: String): Boolean {
        log.debug { "Exportando personajes a fichero CSV" }
        return storageCsv.store(data, Config.storageFileExportCsv)
    }

    override fun storeToJson(data: List<Personaje>, fileName: String): Boolean {
        log.debug { "Exportando personajes a fichero JSON" }
        return storageJson.store(data, Config.storageFileExportJson)
    }

    override fun loadFromCsv(fileName: String): List<Personaje> {
        log.debug { "Cargando personajes desde fichero CSV" }
        return storageCsv.load(Config.storageFileImportCsv)
    }

    override fun loadFromJson(fileName: String): List<Personaje> {
        log.debug { "Cargando personajes desde fichero JSON" }
        return storageJson.load(Config.storageFileImportJson)
    }
}