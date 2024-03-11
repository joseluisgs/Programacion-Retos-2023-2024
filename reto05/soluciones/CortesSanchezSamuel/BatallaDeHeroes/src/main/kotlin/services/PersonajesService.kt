package services

import dto.PersonajeDto
import mappers.toPersonaje
import models.Personaje
import java.io.File

class PersonajesService {

    private val _personajes= mutableListOf<Personaje>()
    val personajesFile : List<Personaje>
        get() = _personajes.toList()


    init {
        val file = ClassLoader.getSystemResource("personajes.csv").toURI()
        _personajes.addAll(readFromFile(File(file)))
    }


    private fun readFromFile(personajeFile: File): List<Personaje> {
        val personajes = personajeFile.readLines()
            .drop(1)
            .map { line -> line.split(",") }
            .map { parts ->
                PersonajeDto(
                    id = parts[0],
                    nickname = parts[1],
                    nombre = parts[2],
                    edad = parts[3],
                    vivo = parts[4],
                    villano = parts[5],
                    habilidad = parts[6],
                    pc = parts[7]
                )
            }
            .map { it.toPersonaje() }

        //personajes.forEach { println(it) }
        return personajes
    }

}