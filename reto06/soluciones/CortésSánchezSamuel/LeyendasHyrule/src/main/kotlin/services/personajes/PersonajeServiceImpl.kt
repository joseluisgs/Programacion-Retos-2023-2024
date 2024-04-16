package services.personajes

import exceptions.personaje.PersonajeException
import models.Personaje
import repositories.personajes.PersonajeRepository
import services.cache.PersonajesCache
import services.storage.Storage

class PersonajesServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personasRepository: PersonajeRepository,
    private val personasCache: PersonajesCache
):PersonajeService {
    override fun loadFromCsv(file: String): List<Personaje> {
        return storageCsv.load(file)
    }

    override fun storeToCsv(personajes: List<Personaje>) {
        storageCsv.store(personajes)
    }

    override fun loadFromJson(file: String): List<Personaje> {
        return storageJson.load(file)
    }

    override fun storeToJson(personajes: List<Personaje>) {
        storageJson.store(personajes)
    }

    override fun findAll(): List<Personaje> {
        return personasRepository.findAll()
    }

    override fun findByName(name: String): Personaje {
        return personasCache.get(name) ?: personasRepository.findByNombre(name)?. also { personasCache.put(name,it) }
        ?: throw PersonajeException.PersonajeNotFetchedException()
    }

    override fun save(personaje: Personaje): Personaje {
        return personasRepository.save(personaje).also {
            personasCache.put(personaje.nombre, it)
        }
    }

    override fun update(name: String, personaje: Personaje): Personaje {
        return personasRepository.update(name, personaje).also {
            personasCache.put(name, it!!)
        } ?: throw PersonajeException.PersonajeNotUpdatedException()
    }

    override fun delete(name: String, logical: Boolean): Personaje {
        return personasRepository.delete(name,logical).also {
            personasCache.remove(name)
        } ?: throw PersonajeException.PersonajeNotFetchedException()
    }

}