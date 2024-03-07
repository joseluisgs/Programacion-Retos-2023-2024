import dto.PersonajeDto
import mappers.toPersonaje
import models.Personaje
import services.PersonajesService
import java.io.File
import kotlin.io.path.*

fun main() {

    val personaje = PersonajesService()

    /*Habilidad de un Personaje Específico: Solicita al usuario que ingrese el ID de un personaje y muestra sus habilidades.
    Listado de los personajes con ID par
    Personaje Más Viejo: mostrando solo su nombre y edad.
    Personaje Más Joven
    Personajes que hayan fallecido
    Villanos en la Base de Datos: Filtra y muestra la lista de todos los personajes que son villanos.
    Héroes Vivos: Encuentra y muestra la lista de todos los héroes que están actualmente vivos.
    Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70
    Agrupar Personajes por Habilidad: Realiza un conteo de cuántos personajes tienen cada habilidad y muestra los resultados.*/

//Habilidad de un Personaje Específico: Solicita al usuario que ingrese el ID de un personaje y muestra sus habilidades.
    println()
    println("Habilidad de un personaje especifico:")
    val cadena = readLine()
    val personajesEncontrados = personaje.personajesFile.filter { it.nickname == cadena }
    if (personajesEncontrados.isNotEmpty()) {
        val habilidades = personajesEncontrados.map { it.habilidad }
        println("La habilidad de $cadena es $habilidades")
    } else {
        println("No se encontró ningún personaje con el apodo '$cadena'.")
    }


//Listado de los personajes con ID par
    println()
    println("Personajes con ID par")
    val personajesIdPar = personaje.personajesFile.filter { it.id % 2 == 0 }
        .forEach{ println(it) }

//Personaje Más Viejo: mostrando solo su nombre y edad.

    println()
    println("Personaje mas viejo")
    val personajeMasViejo = personaje.personajesFile.maxBy { it.edad }
        .also { println("El personaje mas viejo es ${it.nickname} con ${it.edad} años") }


//Personaje Más Joven

    println()
    println("Personaje mas joven")
    val personajeMasJoven = personaje.personajesFile.minBy { it.edad }
        .also { println("El personaje mas joven es ${it.nickname} con ${it.edad} años") }

//Personajes que hayan fallecido

    println()
    println("Personajes que hayan fallecido")
    val personajesFallecidos = personaje.personajesFile.filter { it.vivo == false }
        .forEach{ println(it) }

//Villanos en la Base de Datos: Filtra y muestra la lista de todos los personajes que son villanos.

    println()
    println("Villanos de la base de datos")
    val personajesVillanos = personaje.personajesFile.filter { it.villano == true }
        .forEach{ println(it) }

//Héroes Vivos: Encuentra y muestra la lista de todos los héroes que están actualmente vivos.

    println()
    println("Heroes vivos actualmente")
    val personajesVivos = personaje.personajesFile.filter { it.villano == false && it.vivo == true }
        .forEach{ println(it) }

//Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70

    println()
    println("Personajes con edad menor a 40 y pc mayor que 70")
    val personajesEdadMenorPcMayor = personaje.personajesFile.filter { it.edad < 40 && it.pc > 70}
        .forEach{ println(it) }

//Agrupar Personajes por Habilidad: Realiza un conteo de cuántos personajes tienen cada habilidad y muestra los resultados.

    println()
    println("Agrupar personajes por habilidad")
    val personajesConteoHabilidad = personaje.personajesFile.groupBy { it.habilidad }
        .mapValues { it.value.size }
        .forEach{(nombreHabilidad , conteo) ->
            println("$nombreHabilidad : $conteo")
        }

}

