package org.example.services.storage

import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageExceptions
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import java.time.LocalDate
import kotlin.io.path.Path

private val logger = logging()

class StoragePersonajesCsv: Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero csv" }
        try {
            val file = Path(Config.storageData, "personajes-back.csv").toFile()
            file.writeText("Tipo,Nombre,Habilidad,Ataque,Edad,Arma,CreatedAt,UpdatedAt,IsDeleted\n")
            data.map { it.toPersonajeDto() }
                .forEach {
                    file.appendText("${it.tipo},${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma},${it.created_at},${it.updated_at},${it.is_deleted}\n")
                }
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero csv de personajes: ${e.message}" }
            throw StorageExceptions.StoreException("Error al guardar el fichero csv de personas: ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero csv" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageExceptions.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    PersonajeDto(
                        tipo = data[0],
                        nombre = data[1],
                        habilidad = data[2],
                        ataque = data[3].toInt(),
                        edad = data[4].toInt(),
                        arma = data[5],
                        created_at = LocalDate.now().toString(),
                        updated_at = LocalDate.now().toString(),
                        is_deleted = false
                    ).toPersonaje()
                }
        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero csv de personajes: ${e.message}" }
            e.printStackTrace()
            throw StorageExceptions.LoadException("Error al cargar el fichero csv de personajes: ${e.message}")
        }
    }
}