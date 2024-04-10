package services.storage

import config.Config
import dto.PersonajeDto
import exceptions.storage.StorageExceptions
import mappers.toPersonaje
import mappers.toPersonajeDto
import models.Personaje
import services.cache.Cache
import java.io.File
import kotlin.io.path.Path

class StoragePersonajesCSV:Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        val file= Path(Config.storageData,"personajes-back.csv").toFile()
        file.writeText("Nombre,Tipo,Habilidad,Ataque,Edad,Arma\n")
        data.map{it.toPersonajeDto()}.forEach {
            file.appendText("${it.nombre},${it.tipo},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
        }
        return true
    }

    override fun load(fileName: String): List<Personaje> {
        val file = Path(Config.storageData, fileName).toFile()
        if (!file.exists()) throw StorageExceptions.FicheroNoEncontrado()
        return file.readLines().drop(1).map {
            val data = it.split(",")
            PersonajeDto(
                nombre = data[0],
                tipo = data[1],
                habilidad = data[2],
                ataque = data[3].toInt(),
                edad = data[4].toInt(),
                arma = data[5],
                isDeleted = null
            ).toPersonaje()
        }
    }
}