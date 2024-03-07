import dto.PersonajesDto
import mappers.toPersonaje
import models.Personaje
import services.PersonajesServices
import java.io.File

fun main() {

    val fileName = "personajes.csv"
    val file = object {}.javaClass.getResource("/$fileName")

    if (file != null) {
        println("Fichero encontrado en: ${file.path}")
        procesarFichero(file.file)
    } else {
        println("Fichero no encontrado")
    }
}

fun procesarFichero(file: String) {
    val personajesServices = PersonajesServices()
    val file = File(file)
    val lista = file.readLines()
        .drop(1)
        .map { linea -> linea.split(",") }
        .map { parts ->
            PersonajesDto(
                id = parts[0],
                nickName = parts[1],
                nombre = parts[2],
                edad = parts[3],
                personajeVivo = parts[4],
                villano = parts[5],
                habilidad = parts[6],
                pc = parts[7]
            )
        }
        .map { it.toPersonaje() }

    lista.forEach { println(it) }

    println()

    println("CONSULTAS")

    println()

    println()
    println("Habilidad de un personaje especifico:")
    //Habilidad de un personaje específico.
    val personajeEspecificoHabilidad = lista.filter { it.nickName == "Spider-Man" }
        .map { it.habilidad }
    println("La habilidad de Spider-Man es $personajeEspecificoHabilidad ")

    println()
    println("Listado de los personajes con ID par")
    //Listado de los personajes con ID par.
    val personajesIDPar = lista.filter { it.id % 2 == 0 }
    personajesIDPar.forEach { println("Id: ${it.id} con nombre: ${it.nombre} ") }

    println()
    println("Personaje más viejo, mostrando nombre y edad:")
    //Personaje más viejo, mostrando nombre y edad.
    val personajeViejo = lista.maxBy { it.edad }.also {
        println("La persona mas vieja es ${it.nombre} y tiene ${it.edad} años")
    }


    println()
    println("Personaje más joven")
    //Personaje más joven.
    val personajeJoven = lista.minBy { it.edad }.also {
        println("La persona mas joven es ${it.nombre} y tiene ${it.edad} años.")
    }

    println()
    println("Personajes que hayan fallecido:")
    //Personajes que hayan fallecido.
    val personajesFallecidos = lista.filter { it.personajeVivo == false }
    personajesFallecidos.forEach { println("Los personajes que han fallecido son: ${it.nombre} ") }


    println()
    println("Villanos en la base de datos:")
    //Villanos en la base de datos.
    val personajesVillanos = lista.filter { it.villano == true }
    personajesVillanos.forEach { println("Los villanos son: ${it.nombre} ") }

    println()
    println("Heroes vivos:")
    //Heroes vivos.
    val personajesVivos = lista.filter { it.personajeVivo == true }
    personajesVivos.forEach { println("Los personajes vivos son: ${it.nombre}") }

    println()
    println("Personajes con edad menor a 40 y puntos de combate mayor a 70:")
    //Personajes con edad menor a 40 y puntos de combate mayor a 70.
    val personajesEdadYPuntos = lista.filter { it.edad <= 40 && it.pc >= 70 }
    personajesEdadYPuntos.forEach { println("${it.nombre}, tiene ${it.edad} años y puntos de combate: ${it.pc}") }

    println()
    println("Personajes que no son heroes:")
    //Personajes que no son heroes.
    val personajesNoHeroes = lista.filter { it.villano == true }
    personajesNoHeroes.forEach { println("${it}") }

    println()
    println("Cuantos personajes tienen cada habilidad:")
    //Agrupar personajes por habilidad.
    val personajesHabilidad = lista.groupBy { it.habilidad }
        .mapValues { it.value.size }
        .forEach { (nombre, habilidad) ->
            println("$nombre: $habilidad")
        }

    // Listas
    val (miLista, listaMaquina) = personajesServices.ListasAleatorias(lista)

    println("Tu lista:")
    println(miLista.toString())

    println("Lista de la Máquina:")
    println(listaMaquina.toString())


    //COMBATE
    val ganador = personajesServices.determinarGanador(miLista, listaMaquina)
    println(ganador)
}


