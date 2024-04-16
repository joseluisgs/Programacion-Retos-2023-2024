package org.example.services.storage.personaje

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

class StoragePersonajesCsv: Storage<Personaje> {
    override fun store(data: List<Personaje>,fileName: String): Boolean {
        log.debug { "Guardando personas en fichero csv" }
        try {
            val file = Path(Config.storageDir, fileName).toFile()
            file.writeText("id,nombre,tipo,clase,habilidad,ataque,edad,arma,created_at\n")
            data.map { it.toPersonajeDto() }
                .forEach {
                    file.appendText("${it.id},${it.nombre},${it.tipo},${it.clase},${it.habilidad},${it.ataque},${it.edad},${it.arma},${it.createdAt}\n")
                }
            log.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            log.error { "Error al guardar el fichero csv de personajes: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero csv de personajes: ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            log.debug { "Cargando personajes desde fichero csv" }
            val file = Path(Config.storageDir, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    PersonajeDto(
                        id = data[0].toLong(),
                        nombre = data[1],
                        tipo = data[2],
                        clase = data[3],
                        habilidad = data[4],
                        ataque = data[5].toInt(),
                        edad = data[6].toInt(),
                        arma = data[7],
                        createdAt = data[8],
                        updatedAt = null,
                        isDeleted = null
                    ).toPersonaje()
                }
        } catch (e: Exception) {
            log.error { "Error al cargar el fichero csv de personas: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero csv de personas: ${e.message}")
        }
    }
}