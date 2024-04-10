package org.example.services.storage

import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageException
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()
class StoragePersonajesCsv: Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        val file= Path(Config.storageData,"personajes-back.csv").toFile()
        file.writeText("Nombre,Tipo,Habilidad,Ataque,Edad,Arma\n")
        data.map{it.toPersonajeDto()}.forEach {
            file.appendText("${it.nombre},${it.tipo},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
        }
        return true
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Carganado personajes desde fichero Csv" }
            val file = Path(Config.storageData, fileName).toFile()
            if(!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    PersonajeDto(
                        nombre = data[1],
                        tipo = data[0],
                        habilidad = data[2],
                        ataque = data[3].toInt(),
                        edad = data[4].toInt(),
                        arma = data[5],
                        isDeleted = null
                    ).toPersonaje()
                }
        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv de personajes: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero csv de personajes: ${e.message}")
        }
    }

}