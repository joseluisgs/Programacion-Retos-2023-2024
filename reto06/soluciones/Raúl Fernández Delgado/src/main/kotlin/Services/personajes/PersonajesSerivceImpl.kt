package Services.personajes

import Services.cache.PersonajesCache
import exceptions.personajes.PersonajesExceptions
import models.Personaje

class PersonajesServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personasRepository: PersonajesRepository,
    private val personasCache: PersonajesCache
): PersonajesService {
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
        return personasCache.get(name) ?: personasRepository.findByName(name)?. also { personasCache.put(name,it) }
        ?: throw PersonajesExceptions.PersonajeNoEncontrado()
    }

    override fun save(personaje: Personaje): Personaje {
        return personasRepository.save(personaje).also {
            personasCache.put(personaje.nombre, it)
        }
    }

    override fun update(name: String, personaje: Personaje): Personaje {
        return personasRepository.update(name, personaje).also {
            personasCache.put(name, it!!)
        } ?: throw PersonajesExceptions.PersonajeNoEncontrado()
    }

    override fun delete(name: String, logical: Boolean): Personaje {
        return personasRepository.delete(name,logical).also {
            personasCache.remove(name)
        } ?: throw PersonajesExceptions.PersonajeNoEncontrado()
    }

}