package org.example

import org.example.cache.CacheImpl
import org.example.config.Config
import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepositoryImpl
import org.example.services.personaje.PersonajesServiceImpl
import org.example.services.storage.personaje.StoragePersonajesCsv
import org.example.services.storage.personaje.StoragePersonajesJson
import org.example.validators.personaje.PersonajesValidator
import org.lighthousegames.logging.logging
import java.time.LocalDate

/**
 * Leyendas de Hyrule: El Repositorio de Personajes
 * @author Germán Fernández Carracedo
 * @since 1.0
 */
private val log = logging()
private var personajes: List<Personaje> = emptyList()
private val personajesService = PersonajesServiceImpl(
    repository = PersonajeRepositoryImpl(),
    storageCsv = StoragePersonajesCsv(),
    storageJson = StoragePersonajesJson(),
    cache = CacheImpl(),
    validator = PersonajesValidator()
)

fun main() {


    var opcion: Int = 0

    do{
        println()
        println("#############################################################")
        println("## Zelda: Leyendas de Hyrule: El Repositorio de Personajes ##")
        println("#############################################################")

        println("1. Importar datos desde fichero")
        println("2. Exportar datos a fichero")
        println("3. Mostrar todos los personajes de la Base de Datos")
        println("4. Ejemplos operaciones sobre la Base de datos")
        println("5. Consultas")
        println()
        println("0. Salir")

        println()
        print("Introduzca una opción: ")

        opcion= readln().toIntOrNull() ?: -1

        when (opcion){
            1-> mostrarSubMenuImportacion()
            2-> mostrarSubMenuExportacion()
            3-> mostrarPersonajesBD()
            4-> mostrarEjemplosOperacionesRepo()
            5-> mostrarConsultas()
            0-> despedida()
            else-> println("Opción no válida")
        }

    } while (opcion != 0)

}

/**
 * Muestra mensaje de despedida
 */
fun despedida() {
    println("Muchas gracias por visitar nuestro Registro")
}

/**
 * Muestra las consultas solicitadas en el enunciado
 */
fun mostrarConsultas() {
    var personajes = personajesService.findAll()
    if (personajes.isEmpty()) {
        println("La base de datos está vacía, no se puede consultar ningún dato")
    } else {
        println()
        println("############################################################################")
        println("Consultas sobre la lista de personajes obtenida de la base de datos")
        println("############################################################################")


        println()
        println("############################################################################")
        println("Tres personajes más mayores:")
        println("############################################################################")
        personajes.sortedByDescending { it.edad }
            .take(3)
            .forEach { println(it) }

        println()
        println("############################################################################")
        println("Agrupar personajes por tipo de arma")
        println("############################################################################")
        personajes.groupBy { it.tipo }
            .forEach{ println("${it.key} -> \n${it.value} \n")}

        println()
        println("############################################################################")
        println("Personajes menores de edad")
        println("############################################################################")
        personajes.filter { it.edad<21 }
            .forEach { println (it) }

        println()
        println("############################################################################")
        println("Personajes con más ataque")
        println("############################################################################")
        personajes.sortedByDescending { it.ataque }
            .take(5)
            .forEach { println(it) }

        println()
        println("############################################################################")
        println("Mostrar a todos los enemigos")
        println("############################################################################")
        personajes.filter { it.tipo == "Enemigo" }
            .forEach { println(it) }

        println()
        println("############################################################################")
        println("Dos personajes con el ataque más fuerte")
        println("############################################################################")
        println()
        personajes.sortedByDescending { it.ataque }
            .take(2)
            .forEach { println(it) }
    }
}


fun mostrarEjemplosOperacionesRepo() {
    println()
    println("############################################################################")
    println("Ejemplos de operaciones con el repositorio")
    println("############################################################################")
    println()
    println("############################################################################")
    println("Guardando personaje de ejemplo")
    println("############################################################################")

    val personajeEjemplo=personajesService.save(Personaje(
        nombre="Nombre Ejemplo",
        tipo="Guerrero",
        clase="Clase Ejemplo",
        habilidad="Habilidad Ejemplo",
        ataque = 50,
        edad = 100,
        arma="Arma Ejemplo",
        createdAt =LocalDate.now(),
        updatedAt = LocalDate.now(),
        isDeleted = false
    ))
    println()
    println (personajeEjemplo)

    println()
    println("############################################################################")
    println("Modificando personaje de ejemplo")
    println("############################################################################")
    println("Original: $personajeEjemplo")

    println("Modificado: ${personajesService.update(personajeEjemplo.id,personajeEjemplo.copy(
        clase="Clase Modificada", 
        edad=1000,
        arma = "Arma Modificada"))}")

    println()
    println("############################################################################")
    println("Eliminando personaje de ejemplo (borrado lógico)")
    println("############################################################################")
    personajesService.delete(personajeEjemplo.id,true)
    println()
    println("############################################################################")
    println("Mostrando personaje eliminado (borrado lógico)")
    println("############################################################################")
    println("Eliminado: ${personajesService.findById(personajeEjemplo.id)}")

    println()
    println("############################################################################")
    println("Eliminando personaje de ejemplo (borrado físico)")
    println("############################################################################")
    personajesService.delete(personajeEjemplo.id,false)

    println()
    println("############################################################################")
    println("Mostrando personajes por tipo \"Guerrero\" (consulta a BD)")
    println("############################################################################")
    var personajes = personajesService.findByType("Guerrero")
    if (personajes.size > 0) {
        personajes.forEach { println(it) }
    } else { println ("No se ha encontrado ningún personaje del tipo elegido") }
    println()
    println("############################################################################")
    println("Mostrando personajes por tipo \"Enemigo\" (consulta a BD)")
    println("############################################################################")
    personajes = personajesService.findByType("Enemigo")
    if (personajes.size > 0) {
        personajes.forEach { println(it) }
    } else { println ("No se ha encontrado ningún personaje del tipo elegido") }

    println()
    println("############################################################################")
    println("Mostrando personajes por tipo \"Otro\" (consulta a BD)")
    println("############################################################################")
    personajes = personajesService.findByType("Otro")
    if (personajes.size > 0) {
        personajes.forEach { println(it) }
    } else { println ("No se ha encontrado ningún personaje del tipo elegido") }
}

fun mostrarSubMenuExportacion() {
    do {
        println()
        println("Exportar datos de la BD a fichero")
        println("============================")
        println("1. Exportar a CSV: ${Config.storageFileExportCsv}")
        println("2. Exportar a JSON: ${Config.storageFileExportJson}")
        println()
        println("0. Volver ")
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> exportarACsv()
            2-> exportarAJson()
        }
    } while (opcion!= 0)
}

fun exportarAJson() {
    println()
    println("############################################################################")
    println("Exportando datos de personajes a fichero JSON ${Config.storageFileExportJson}")
    println("############################################################################")
    personajes = personajesService.findAll()
    if (personajes.size>0) {
        personajesService.storeToJson(personajes, Config.storageFileExportJson)
    } else {
        println("Error, no se puede exportar porque la base de datos está vacía")
    }
}

fun exportarACsv() {
    println()
    println("############################################################################")
    println("Exportando datos de personajes a fichero CSV ${Config.storageFileExportCsv}")
    println("############################################################################")
    personajes = personajesService.findAll()
    if (personajes.size>0) {
        personajesService.storeToCsv(personajes, Config.storageFileExportCsv)
    } else {
        println("Error, no se puede exportar porque la base de datos está vacía")
    }
}

fun mostrarSubMenuImportacion (){
    do {
        println()
        println("Importar datos desde fichero")
        println("============================")
        println("1. Desde CSV: ${Config.storageFileImportCsv}")
        println("2. Desde JSON: ${Config.storageFileImportJson}")
        println()
        println("0. Volver ")
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> importarDesdeCsv()
            2-> importarDesdeJson()
        }
    } while (opcion!= 0)
}


private fun importarDesdeCsv() {
    println()
    println("############################################################################")
    println("1. Importando datos de personajes desde fichero CSV ${Config.storageFileImportCsv}")
    println("############################################################################")
    personajes = personajesService.loadFromCsv(Config.storageFileImportCsv)
    log.debug { "Personajes importados a lista de personajes" }
    personajes.forEach { println(it) }

    println()
    println("############################################")
    println("2. Guardando personajes en la Base de Datos")
    println("############################################")
    personajes.forEach {
        personajesService.save(it)
    }
}

private fun importarDesdeJson(){
    println()
    println("############################################################################")
    println("1. Importando datos de personajes desde fichero JSON ${Config.storageFileImportJson}")
    println("############################################################################")
    personajes = personajesService.loadFromJson(Config.storageFileImportJson)
    log.debug { "Personajes importados a lista de personajes" }
    personajes.forEach { println(it) }

    println("############################################")
    println("2. Guardando personajes en la Base de Datos")
    println("############################################")
    personajes.forEach {
        personajesService.save(it)
    }
}

private fun mostrarPersonajesBD() {
    println()
    println("######################################################")
    println("3. Mostrando todos los personajes de la Base de Datos")
    println("######################################################")
    val personajes = personajesService.findAll()
        if (personajesService.findAll().size>0) {
            personajes.forEach { println(it) }
        } else {
            println("La base de datos está vacía")
        }
}

