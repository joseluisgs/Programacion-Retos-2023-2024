package org.example.service.storage

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageExceptions
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()
/**
 * Implementación de [Storage] que almacena y carga personajes desde un archivo JSON.
 */

class StoragePersonajeJson: Storage<Personaje> {

    /**
     * Almacena una lista de personajes en un archivo JSON.
     * @param data La lista de personajes a almacenar.
     * @return true si la operación de almacenamiento fue exitosa, false de lo contrario.
     * @throws StorageExceptions.StoreExceptions Si hay un error al guardar el archivo JSON.
     */
    @OptIn(ExperimentalSerializationApi::class)
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero json" }
        try {
            val file = Path(Config.storageData, "personajes-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            file.writeText(json.encodeToString<List<PersonajeDto>>(data.map { it.toPersonajeDto() }))
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero json de personajes: ${e.message}" }
            throw StorageExceptions.StoreExceptions("Error al guardar el fichero json de personajes: ${e.message}")
        }
    }
    /**
     * Carga personajes desde un archivo JSON.
     * @param fileName El nombre del archivo desde donde cargar los personajes.
     * @return Una lista de personajes cargados desde el archivo JSON.
     * @throws StorageExceptions.LoadExceptions Si hay un error al cargar el archivo JSON.
     */

    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero json" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageExceptions.LoadExceptions("El fichero $fileName no existe")
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            return json.decodeFromString<List<PersonajeDto>>(file.readText())
                .map { it.toPersonaje() }

        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero json de personajes: ${e.message}" }
            throw StorageExceptions.LoadExceptions("Error al cargar el fichero json de personajes: ${e.message}")
        }
    }
}