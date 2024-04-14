package org.example.services.personajes

import org.example.exceptions.personajes.PersonajesException
import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging

/**
 * Implementación de [PersonajeService] que proporciona operaciones para gestionar personajes.
 *
 * @property storageCsv Almacenamiento para datos de personajes en formato CSV.
 * @property storageJson Almacenamiento para datos de personajes en formato JSON.
 * @property personajesRepository Repositorio para acceder a los datos de personajes.
 * @property personajesCache Cache para almacenar en memoria los datos de personajes.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class PersonajeServiceImpl(
    private val storageCsv: Storage<Personaje>,
    private val storageJson: Storage<Personaje>,
    private val personajesRepository: PersonajesRepository,
    private val personajesCache: PersonajesCache
) : PersonajeService {
    /**
     * Carga los personajes desde un archivo CSV.
     *
     * @return Lista de personajes cargados desde el archivo CSV.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun loadFromCsv(): List<Personaje> {
        logging().debug { "Cargando personajes desde CSV" }
        return storageCsv.load("personajes.csv")
    }

    /**
     * Almacena los personajes en un archivo CSV.
     *
     * @param personajes Lista de personajes a almacenar.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun storeToCsv(personajes: List<Personaje>) {
        logging().debug { "Guardando personajes en CSV" }
        storageCsv.store(personajes)
    }

    /**
     * Carga los personajes desde un archivo JSON.
     *
     * @return Lista de personajes cargados desde el archivo JSON.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun loadFromJson(): List<Personaje> {
        logging().debug { "Cargando personajes desde JSON" }
        return storageJson.load("personajes-back.json")
    }

    /**
     * Almacena los personajes en un archivo JSON.
     *
     * @param personajes Lista de personajes a almacenar.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun storeToJson(personajes: List<Personaje>) {
        logging().debug { "Guardando personajes en JSON" }
        storageJson.store(personajes)
    }

    /**
     * Busca y devuelve todos los personajes.
     *
     * @return Lista de todos los personajes.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findAll(): List<Personaje> {
        logging().debug { "Buscando todos los personajes" }
        return personajesRepository.findAll()
    }

    /**
     * Busca y devuelve los personajes del tipo especificado.
     *
     * @param tipo Tipo de personaje a buscar.
     * @return Lista de personajes del tipo especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findByTipo(tipo: String): List<Personaje> {
        logging().debug { "Buscando personajes por tipo $tipo" }
        return personajesRepository.findByTipo(tipo)
    }

    /**
     * Busca y devuelve un personaje por su identificador.
     *
     * @param id Identificador del personaje a buscar.
     * @return El personaje encontrado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findById(id: Long): Personaje {
        logging().debug { "Buscando personajes por id $id" }
        return personajesCache.get(id) ?: personajesRepository.findById(id)?.also {
            personajesCache.put(id, it)
        } ?: throw PersonajesException.PersonajesNotFoundException("Personaje no encontrado con id $id")

    }

    /**
     * Guarda un nuevo personaje.
     *
     * @param personaje El personaje a guardar.
     * @return El personaje guardado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun save(personaje: Personaje): Personaje {
        logging().debug { "Guardando personaje $personaje" }
        return personajesRepository.save(personaje).also {
            personajesCache.put(personaje.id, it)
        }
    }

    /**
     * Actualiza un personaje existente.
     *
     * @param id Identificador del personaje a actualizar.
     * @param personaje Datos actualizados del personaje.
     * @return El personaje actualizado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun update(id: Long, personaje: Personaje): Personaje {
        logging().debug { "Actualizando personaje con id $id" }
        return personajesRepository.update(id, personaje).also {
            personajesCache.put(id, it!!)
        } ?: throw PersonajesException.PersonajesNotFoundException("Personaje no encontrado con id $id")
    }

    /**
     * Elimina un personaje por su identificador.
     *
     * @param id Identificador del personaje a eliminar.
     * @param logical Indica si se debe realizar un borrado lógico (marcar como eliminado) o físico.
     * @return El personaje eliminado.
     * @throws NoSuchElementException si no se encuentra ningún personaje con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun delete(id: Long, logical: Boolean): Personaje {
        logging().debug { "Borrando personaje con id $id" }
        return personajesRepository.delete(id).also {
            personajesCache.remove(id)
        } ?: throw PersonajesException.PersonajesNotFoundException("Personaje no encontrado con id $id")
    }
}
