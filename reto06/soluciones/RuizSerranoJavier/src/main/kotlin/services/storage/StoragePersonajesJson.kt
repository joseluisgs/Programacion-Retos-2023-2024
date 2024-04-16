package org.example.services.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.exceptions.storage.StorageException
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val log = logging()
class StoragePersonajesJson: Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        log.debug { "Guardando personas en fichero json" }
        try {
            val file = Path(Config.storageData, "personas-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            file.writeText(json.encodeToString<List<Personaje>>(data.map { it }))
            log.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            log.error { "Error al guardar el fichero json de personas: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero json de personas: ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
        log.debug { "Cargando personas desde fichero json" }
        val file = Path(Config.storageData, fileName).toFile()
        if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        return json.decodeFromString<List<Personaje>>(file.readText())
            .map { it }

        } catch (e: Exception) {
        log.error { "Error al cargar el fichero json de personas: ${e.message}" }
        throw StorageException.LoadException("Error al cargar el fichero json de personas: ${e.message}")
        }
    }
}