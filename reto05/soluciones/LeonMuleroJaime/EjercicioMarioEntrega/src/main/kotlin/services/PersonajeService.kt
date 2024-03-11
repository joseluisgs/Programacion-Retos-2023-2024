package services

import models.Personaje
import kotlin.io.path.*

class PersonajeService {

    private val file = Path("data", "personajes.csv")

    fun generarListaPersonajes(): List<Personaje> {

        val listaPersonajes = file.readLines()
            .drop(1)
            .map { line -> line.split(",") }
            .map { parts ->
                Personaje(
                    id = parts[0].toInt(),
                    nickName = parts[1],
                    nombre = parts[2],
                    edad = parts[3].toInt(),
                    vivo = parts[4].toBoolean(),
                    villano = parts[5].toBoolean(),
                    habilidad = parts[6],
                    puntosCombate = parts[7].toInt(),
                )
            }

        return listaPersonajes
    }
}