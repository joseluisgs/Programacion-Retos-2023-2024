package org.example.services.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageError
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.io.path.Path

private val logger = logging()
class StoragePersonajesCsv: Storage<Personaje> {
    override fun store(data: List<Personaje>): Result<Unit, StorageError> {
        val file= Path(Config.storageData,"personajes-back.csv").toFile()
        return try {
            file.writeText("Nombre,Tipo,Habilidad,Ataque,Edad,Arma\n")
            Ok(data.map{it.toPersonajeDto()}.forEach {
                file.appendText("${it.nombre},${it.tipo},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
            })
        }catch (e: Exception){
            logger.error { "Error al guardar el fichero csv de personajes: ${file.absolutePath}. ${e.message}" }
            Err(StorageError.StoreError("Error al guardar personajes desde el fichero ${file.absolutePath}. ${e.message}"))
        }


    }

    override fun load(fileName: String): Result<List<Personaje>, StorageError> {
        logger.debug { "Carganado personajes desde fichero Csv" }
        val file = Path(Config.storageData, fileName).toFile()
        return try {
            Ok( file.readLines().drop(1)
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
                })
        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv de personajes: ${file.absolutePath}. ${e.message}" }
            Err(StorageError.LoadError("Error al cargar personajes desde el fichero ${file.absolutePath}. ${e.message}"))
        }
    }

}
