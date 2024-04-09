package org.example.services.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageException
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()
class StoragePersonajesJson:Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero json" }
        try {
            val file = Path(Config.storageData, "personajes-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            file.writeText(json.encodeToString<List<PersonajeDto>>(data.map { it.toPersonajeDto() }))
            return true
        }catch (e: Exception){
            logger.error { "Error al guardar el fichero json de personajes: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero json de personajes ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero json" }
            val file = Path(Config.storageData, fileName).toFile()
            if(!file.exists()) throw StorageException.LoadException("El ficher $fileName no existe")
            val json = Json{
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            return json.decodeFromString<List<PersonajeDto>>(file.readText())
                .map { it.toPersonaje() }
        }catch (e: Exception){
            logger.error { "Erro al cargar el fcihero de personas: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero json de personajes ${e.message}")
        }
    }

}