import org.example.exceptions.PersonajeEsception
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import services.storage.StoragePersonajesCsv
import org.lighthousegames.logging.logging


private val logger = logging()

class PersonajesServiceImpl(
    private val storageCsv: StoragePersonajesCsv,
    private val personasRepository: PersonajesRepository,
    private val personasCache: PersonajesCache
) : PersonajesService {
    override fun loadFromCsv(): List<PersonajeDto> {
        logger.debug { "Cargando personas desde CSV" }
        return storageCsv.load("personas.csv")
    }

    override fun storeToCsv(personas: List<PersonajeDto>) {
        logger.debug { "Guardando personas en CSV" }
        storageCsv.store(personas)
    }

    override fun loadFromJson(): List<PersonajeDto> {
        logger.debug { "Cargando personas desde JSON" }
        return storageCsv.load("personas-back.json")
    }

    override fun storeToJson(personas: List<PersonajeDto>) {
        logger.debug { "Guardando personas en JSON" }
        storageCsv.store(personas)
    }

    override fun findAll(): List<PersonajeDto> {
        logger.debug { "Buscando todas las personas" }
        return personasRepository.findAll()
    }

    override fun findByTipo(tipo: String): List<PersonajeDto> {
        logger.debug { "Buscando personas por tipo $tipo" }
        return personasRepository.findByTipo(tipo)
    }

    fun findById(id: Long): Any {
        logger.debug { "Buscando persona por id $id" }
        // Buscamos en cache y si no está, en la base de datos y lo guardamos en cache si no está
        return personasCache.get(id) ?: personasRepository.findById(id)?.also {
            personasCache.put(id, it)
        } ?: throw PersonajeEsception.PersonajeNotFoundException("Persona no encontrada con id $id")

    }

    override fun save(persona: PersonajeDto): PersonajeDto {
        logger.debug { "Guardando persona $persona" }
        return personasRepository.save(persona).also {
            personasCache.put(persona.id, it)
        }
    }

    fun update(id: Long, persona: PersonajeDto): PersonajeDto {
        logger.debug { "Actualizando persona con id $id" }
        // Actualizamos en cache y en la base de datos
        return personasRepository.update(id, persona).also {
            personasCache.put(id, it!!)
        } ?: throw PersonajeEsception.PersonajeNotFoundException("Persona no encontrada con id $id")
    }

    fun delete(id: Long, logical: Boolean): PersonajeDto {
        logger.debug { "Borrando persona con id $id" }
        return personasRepository.delete(id).also {
            personasCache.remove(id)
        } ?: throw PersonajeEsception.PersonajeNotFoundException("Persona no encontrada con id $id")
    }
}
