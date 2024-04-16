package services.storage

import config.Config
import exceptions.storage.StorageException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Personaje
import kotlin.io.path.Path

class StoragePersonasJson : Storage<Personaje> {


    @OptIn(ExperimentalSerializationApi::class)
    override fun store(data: List<Personaje>): Boolean {
        println("Guardando personas en fichero json")
        try {
            val file = Path(Config.storageData, "personas-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            file.writeText(json.encodeToString<List<Personaje>>(data))
            println("Guardado correctamente")
            return true
        } catch (e: Exception) {
            println("Error al guardar el fichero json de personas: ${e.message}")
            throw StorageException.StoreException("Error al guardar el fichero json de personas: ${e.message}")
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun load(fileName: String): List<Personaje> {
        try {
            println("Cargando personas desde fichero json")
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            return json.decodeFromString<List<Personaje>>(file.readText())

        } catch (e: Exception) {
            println("Error al cargar el fichero json de personas: ${e.message}" )
            throw StorageException.LoadException("Error al cargar el fichero json de personas: ${e.message}")
        }
    }
}