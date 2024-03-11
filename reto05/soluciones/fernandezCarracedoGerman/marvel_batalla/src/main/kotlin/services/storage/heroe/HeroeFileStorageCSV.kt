package org.example.services.storage.heroe

import org.example.dto.HeroeDto
import org.example.exceptions.storage.StorageExceptions
import org.example.mappers.toHeroe
import org.example.models.Heroe
import org.example.services.storage.base.FileStorage
import java.io.File
import org.lighthousegames.logging.logging

private val log = logging()

/**
 * Clase que gestiona el almacenamiento de personajes en fichero formato CSV
 */
class HeroeFileStorageCSV: FileStorage<Heroe> {

    /**
     * Leemos de un fichero CSV todos los elementos y los devolvemos en una lista de héroes
     * @param file Fichero donde están los datos
     * @return List Lista con los personajes
     */
    override fun readFromFile(file: File): List<Heroe> {
        log.debug { "Leyendo fichero de personajes CSV: $file" }

        if (!file.exists()) {
            log.error { "El fichero CSV de personajes no existe: $file" }
            throw StorageExceptions.FileNotFound("El fichero CSV de personajes no existe: $file")
        }

        return file.readLines()
            .drop(1)
            .map {
                it.split(",")
            }.map{ //parts ->
                //HeroeDto(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6]).toHeroe()
                HeroeDto(
                    id = it[0],
                    nickName = it[1],
                    nombre = it[2],
                    edad = it[3],
                    vivo = it[4],
                    villano = it[5],
                    habilidad = it[6],
                    puntosCombate = it[7]
                ).toHeroe()
            }
    }

    /**
     * Guardamos en el fichero CSV todos los elementos pasados en la lista de héroes
     * @param list Lista de heroes
     * @param file Fichero donde se guardarán los elementos de la lista
     */
    override fun writeToFile(list: List<Heroe>, file: File) {

        log.debug { "Escribiendo en fichero de personajes CSV: $file" }

        file.writeText("ID,NickName,Nombre,Edad,Vivo,Villano,Habilidad,PC\n")

        list.forEach {
            file.appendText("${it.id},${it.nickName},${it.nombre},${it.edad},${it.vivo},${it.villano},${it.habilidad},${it.puntosCombate}\n")
        }

    }
}