package services.storage

import config.Config
import dto.PersonajeDto
import exceptions.storage.StorageExceptions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mappers.toPersonaje
import mappers.toPersonajeDto
import models.Personaje
import java.io.File
import kotlin.io.path.Path

class StoragePersonajeJSON:Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        val file = Path(Config.storageData, "personajes-back.json").toFile()
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        file.writeText(json.encodeToString<List<PersonajeDto>>(data.map { it.toPersonajeDto() }))
        return true
    }

    override fun load(fileName: String): List<Personaje> {
        val file = Path(Config.storageData, fileName).toFile()
        if (!file.exists()) throw StorageExceptions.FicheroNoEncontrado()
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        return json.decodeFromString<List<PersonajeDto>>(file.readText())
            .map { it.toPersonaje() }
    }

}