package org.example.services.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageError
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()
class StoragePersonajesJson:Storage<Personaje> {
    override fun store(data: List<Personaje>): Result<Unit, StorageError> {
        logger.debug { "Guardando personajes en fichero json" }
        val file = Path(Config.storageData, "personajes-back.json").toFile()
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(file.writeText(json.encodeToString<List<PersonajeDto>>(data.map { it.toPersonajeDto() })))
        }catch (e: Exception){
            logger.error { "Error al guardar el fichero json de personajes: ${file.absolutePath}. ${e.message}" }
            Err(StorageError.StoreError("Error al guardar el fichero json de personajes: ${file.absolutePath}. ${e.message}"))
        }
    }

    override fun load(fileName: String): Result<List<Personaje>, StorageError> {
        logger.debug { "Cargando personajes desde fichero json" }
        val file = Path(Config.storageData, fileName).toFile()
        return try {
            val json = Json{
                prettyPrint = true
                ignoreUnknownKeys = true
            }
             Ok(json.decodeFromString<List<PersonajeDto>>(file.readText())
                 .map { it.toPersonaje() })
        }catch (e: Exception){
            logger.error { "Erro al cargar el fcihero de personas: ${file.absolutePath}. ${e.message}" }
            Err(StorageError.LoadError("Error al cargar el fichero json de personajes: ${file.absolutePath}. ${e.message}"))
        }
    }

}