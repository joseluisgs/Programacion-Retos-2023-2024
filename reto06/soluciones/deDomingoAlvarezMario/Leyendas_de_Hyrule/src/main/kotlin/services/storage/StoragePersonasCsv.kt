package services.storage

import PersonajeDto
import org.example.config.Config
import org.example.exceptions.StorageException
import org.example.mappers.toPersonaje
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

class StoragePersonajesCsv : Storage<PersonajeDto> {
    override fun store(data: List<PersonajeDto>): Boolean {
        logger.debug { "Guardando personas en fichero csv" }
        try {
            val file = Path(Config.storageData, "personas-back.csv").toFile()
            file.writeText("id,nombre,habilidad,ataque,edad,arma\n")
            data.map { it.toPersonaje() }
                .forEach {
                    file.appendText("${it.id},${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
                }
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero csv de personas: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero csv de personas: ${e.message}")
        }
    }

    override fun load(fileName: String): List<PersonajeDto> {
        try {
            logger.debug { "Cargando personas desde fichero csv" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    PersonajeDto(
                        id = data[0].toLong(),
                        tipo = "",
                        nombre = data[1],
                        habilidad = data[2],
                        ataque = data[3].toInt(),
                        edad = data[4].toInt(),
                        arma = data[5]
                    )
                }
        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero csv de personas: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero csv de personas: ${e.message}")
        }
    }
}