import models.Personaje
import org.lighthousegames.logging.logging
import services.BackupImpl
import services.PersonajeService
import java.time.LocalDate
import kotlin.io.path.Path

fun main() {
    val logger = logging()

    val backup = BackupImpl()
    //backup.backup() //Para comprobar si funciona comentamos esta parte y el archivo se restaurará del backup
    backup.restore()

    val personajeService = PersonajeService()
    val listadoPersonajes = personajeService.generarListaPersonajes()

    var personaje: Personaje?

    logger.debug { "Habilidad de un Personaje Específico: Solicita al usuario que ingrese el ID de un personaje y muestra sus habilidades" }
    println("Introduce el ID de un personaje:")
    val id = readln().toIntOrNull() ?: 0
    if (id !in 1..30) {
        println("No existe ningún usuario con la id $id")
    } else {
        personaje = listadoPersonajes.find { it.id == id }
        println(" - Habilidades de ${personaje!!.nickName}: ${personaje.habilidad}")
    }


    println()


    logger.debug { "Listado de los personajes con ID par" }
    listadoPersonajes.filter { it.id % 2 == 0 }.forEach { println("- $it") }


    println()


    logger.debug { "Personaje Más Viejo (mostrando solo su nombre y edad)" }
    val oldest = listadoPersonajes.maxOf { it.edad }
    personaje = listadoPersonajes.find { it.edad == oldest }
    println("- Nombre: ${personaje!!.nombre}, Edad: ${personaje.edad}")


    println()


    logger.debug { "Personaje Más Joven" }
    val youngest = listadoPersonajes.minOf { it.edad }
    personaje = listadoPersonajes.find { it.edad == youngest }
    println("- $personaje")


    println()


    logger.debug { "Personajes que hayan fallecido" }
    listadoPersonajes.filter { !it.vivo }.forEach { println("- $it") }


    println()


    logger.debug { "Villanos en la Base de Datos: Filtra y muestra la lista de todos los personajes que son villanos" }
    listadoPersonajes.filter { it.villano }.forEach { println("- $it") }


    println()


    logger.debug { "Héroes Vivos: Encuentra y muestra la lista de todos los héroes que están actualmente vivos" }
    listadoPersonajes.filter { !it.villano && it.vivo }.forEach { println("-$it") }


    println()


    logger.debug { "Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70" }
    listadoPersonajes.filter { it.edad <= 40 && it.puntosCombate >= 70}.forEach { println("-$it") }


    println()


    logger.debug { "Personajes que no son Héroes: Identifica y muestra la lista de personajes que son villanos" }
    listadoPersonajes.filter { it.villano }.forEach { println("- $it") }


    println()


    logger.debug { "Agrupar Personajes por Habilidad: Realiza un conteo de cuántos personajes tienen cada habilidad y muestra los resultados" }
    listadoPersonajes.groupingBy { it.habilidad }.eachCount().forEach { println("- $it") }


    println()


    println("🥊 Comienza la lucha de personajes 🥊")
    println("Generando grupos de personajes...")
    val (listadoPersonajesJugador, listadoPersonajesSistema) = generarListadoPersonajes(listadoPersonajes)

    println("\nListado de Personajes para el jugador: 👨🏻")
    listadoPersonajesJugador.forEach { println(it) }

    println("\nListado de Personajes para el sistema: 🖥️")
    listadoPersonajesSistema.forEach { println(it) }

    var victoriasJugador = 0
    var victoriasSistema = 0

    println()

    for (index in 0..4)
    if (listadoPersonajesJugador[index].puntosCombate > listadoPersonajesSistema[index].puntosCombate) {
        victoriasJugador++
        println("En el combate de ${listadoPersonajesJugador[index].nickName} VS ${listadoPersonajesSistema[index].nickName} ganaría el personaje del jugador 👨🏻 (${listadoPersonajesJugador[index].puntosCombate} VS ${listadoPersonajesSistema[index].puntosCombate})")
    } else if (listadoPersonajesJugador[index].puntosCombate < listadoPersonajesSistema[index].puntosCombate) {
        victoriasSistema++
        println("En el combate de ${listadoPersonajesJugador[index].nickName} VS ${listadoPersonajesSistema[index].nickName} ganaría el personaje del sistema 🖥️ (${listadoPersonajesJugador[index].puntosCombate} VS ${listadoPersonajesSistema[index].puntosCombate})")
    } else {
        println("En el combate de ${listadoPersonajesJugador[index].nickName} VS ${listadoPersonajesSistema[index].nickName} habría un empate en puntos de combate 🟡 (${listadoPersonajesJugador[index].puntosCombate} VS ${listadoPersonajesSistema[index].puntosCombate})")
    }

    val file = Path("data", "bitacora.txt").toFile()

    if (victoriasJugador > victoriasSistema) {
        println("\nEl ganador de la batalla es el jugador 👨🏻")
        file.appendText("FECHA: ${LocalDate.now()} 🗓️ | GANADOR: Jugador 👨🏻 | PUNTUACIÓN(👨🏻 ~ 🖥️): $victoriasJugador ~ $victoriasSistema\n")
    } else if (victoriasJugador < victoriasSistema) {
        println("\nEl ganador de la batalla es el sistema 🖥️")
        file.appendText("FECHA: ${LocalDate.now()} 🗓️ | GANADOR: Sistema 🖥️ | PUNTUACIÓN(👨🏻 ~ 🖥️): $victoriasJugador ~ $victoriasSistema\n")
    } else {
        println("\nLa batalla ha acabado en empate 🟡")
        file.appendText("FECHA: ${LocalDate.now()} 🗓️ | GANADOR: Empate  🟡 | PUNTUACIÓN(👨🏻 ~ 🖥️): $victoriasJugador ~ $victoriasSistema\n")
    }

}

fun generarListadoPersonajes(listadoPersonajes: List<Personaje>): Pair <List<Personaje>,List<Personaje>> {
    val listadoJugador: MutableList<Personaje> = mutableListOf()
    val listadoSistema: MutableList<Personaje> = mutableListOf()
    val idsSeleccionadas: MutableList<Int> = mutableListOf()
    var idRandom: Int
    var index: Int = 1

    repeat(10) {
        do {
            idRandom = (1..30).random()
            val personaje = listadoPersonajes.first { it.id == idRandom }
            val vivo = personaje.vivo
        } while (idsSeleccionadas.contains(idRandom) || !vivo)

        val personaje = listadoPersonajes.first { it.id == idRandom }

        if (index in 1..5) listadoJugador.add(personaje)
        if (index in 6..10) listadoSistema.add(personaje)
        idsSeleccionadas.add(idRandom)
        index++
    }

    return Pair(listadoJugador, listadoSistema)
}
