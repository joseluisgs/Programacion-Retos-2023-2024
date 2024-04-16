package services.storage

import config.Config
import exceptions.storage.StorageException
import models.Personaje
import kotlin.io.path.Path


class StoragePersonajeCsv : Storage<Personaje> {


    override fun store(data: List<Personaje>): Boolean {
        println("Guardando personas en fichero csv")
        try {
            val file = Path(Config.storageData, "personas-back.csv").toFile()
            file.writeText("id,nombre,created_at,tipo,modulo,edad\n")

            data.forEach{
                file.appendText("${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
            }
            println("Guardado correctamente")
            return true
        } catch (e: Exception) {
            println("Error al guardar el fichero csv de personas: ${e.message}")
            throw StorageException.StoreException ("Error al guardar el fichero csv de personas: ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            println("Cargando personas desde fichero csv")
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    Personaje(
                        nombre = data[0],
                        habilidad = data[1],
                        ataque = data[2].toInt(),
                        edad = data[3].toInt(),
                        arma = data[4],
                    )
                }
        } catch (e: Exception) {
            println("Error al cargar el fichero csv de personas: ${e.message}")
            throw StorageException.LoadException("Error al cargar el fichero csv de personas: ${e.message}")
        }
    }
}