package org.example.services.storage.personaje

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageException
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.services.storage.base.Storage
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val log = logging()

class StoragePersonajesJson: Storage<Personaje> {
    @OptIn(ExperimentalSerializationApi::class)
    override fun store(data: List<Personaje>, fileName: String): Boolean {
        try {
            log.debug { "Guardando personajes en fichero json" }
            val file = Path(Config.storageDir, fileName).toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            file.writeText(json.encodeToString<List<PersonajeDto>>(data.map { it.toPersonajeDto() }))
            log.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            log.error { "Error al guardar el fichero json de personajes: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero json de personajes: ${e.message}")
        }    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun load(fileName: String): List<Personaje> {
        try {
            log.debug { "Cargando personajes desde fichero json" }
            val file = Path(Config.storageDir, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            return json.decodeFromString<List<PersonajeDto>>(file.readText())
                .map { it.toPersonaje() }

        } catch (e: Exception) {
            log.error { "Error al cargar el fichero json de personajes: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero json de personajes: ${e.message}")
        }
    }

}