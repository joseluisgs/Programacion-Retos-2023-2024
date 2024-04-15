import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.example.config.Config
import org.example.exceptions.StorageException
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

/*class StoragePersonajesJson : Storage<PersonajeDto> {
    @OptIn(ExperimentalSerializationApi::class)
    override fun store(data: List<PersonajeDto>): Boolean {
        logger.debug { "Guardando personas en fichero json" }
        try {
            val file = Path(Config.storageData, "personas-back.json").toFile()
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            file.writeText(json.encodeToString(data.map { it.toPersonajeDto() }))
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero json de personas: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero json de personas: ${e.message}")
        }
    }


    @OptIn(ExperimentalSerializationApi::class)
    override fun load(fileName: String): List<PersonajeDto> {
        try {
            logger.debug { "Cargando personas desde fichero json" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            return json.decodeFromString<List<PersonajeDto>>(file.readText())
                .map { it.toPersonajeDto() }

        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero json de personas: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero json de personas: ${e.message}")
        }
    }
}*/
