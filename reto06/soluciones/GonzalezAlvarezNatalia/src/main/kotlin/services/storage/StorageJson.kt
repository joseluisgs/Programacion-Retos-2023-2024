package org.example.services.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.exceptions.storage.StorageException
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

/**
 * Implementación de la interfaz [Storage] para almacenar y cargar datos de tipo [Personaje] en formato JSON.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class StorageJson : Storage<Personaje> {
    /**
     * Almacena los datos de personajes en un archivo JSON.
     *
     * @param data Lista de personajes a almacenar.
     * @return true si el almacenamiento fue exitoso, false de lo contrario.
     * @throws StorageException.StoreException si ocurre un error durante el almacenamiento.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero JSON" }
        try {
            val file = Path(Config.storageData, "personajes-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            file.writeText(json.encodeToString<List<Personaje>>(data))
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero JSON de personajes: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero JSON de personajes: ${e.message}")
        }
    }

    /**
     * Carga los datos de personajes desde un archivo JSON.
     *
     * @param fileName Nombre del archivo JSON desde el cual cargar los datos.
     * @return Lista de personajes cargados.
     * @throws StorageException.LoadException si ocurre un error durante la carga de datos.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero JSON" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            return json.decodeFromString<List<Personaje>>(file.readText())
        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero JSON de personajes: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero JSON de personajes: ${e.message}")
        }
    }
}
