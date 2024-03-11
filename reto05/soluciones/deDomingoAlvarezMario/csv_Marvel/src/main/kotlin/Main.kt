package org.example

import ToJSON
import controllers.Batalla
import org.example.dto.PersonajesDto
import java.nio.file.Paths
import kotlin.io.path.*

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 */

fun main() {

    // Especificamos el directorio actual
    val archivoCsv = System.getProperty("user.dir")
    println("El directorio actual es: $archivoCsv")
    val databaseDir = Path(archivoCsv, "data")

    // Comprobamos si existe y es un directorio
    if (databaseDir.exists() && databaseDir.isDirectory()) {
        println("El directorio de la base de datos existe")
    } else {
        println("El directorio de la base de datos no existe")
        return // Salir del programa si no existe el directorio
    }
    val dataFile = Path(databaseDir.toString(), "personajes.csv")

    // Comprobamos si existe y es un archivo
    if (dataFile.exists() && !dataFile.isDirectory() && dataFile.extension == "csv" && dataFile.isReadable()) {
        println("El archivo de la base de datos existe")
    } else {
        println("El archivo de la base de datos no existe")
        return // Salir del programa si no existe el archivo
    }
    println()

    // Añadimos los valores según los índices entre comas del csv
    val personajes = dataFile.readLines()
        .drop(1)// ignoramos la cabecera
        .map { line -> line.split(",") }
        .map { parts ->
            PersonajesDto(
                id = parts[0].toInt(),
                nickName = parts[1],
                nombre = parts[2],
                edad = parts[3].toInt(),
                vivo = parts[4].toBoolean(),
                villano = parts[5].toBoolean(),
                habilidades = parts[6],
                puntosCombate = parts[7].toInt()
            )
        }

    procesarFichero(personajes)

    val batalla = Batalla()
    batalla.batalla(personajes, databaseDir)

    val toJSON = ToJSON()
    val rutaArchivoJson = Paths.get(databaseDir.toString(), "personajes.json").toString()
    val rutaArchivoZip = Paths.get(databaseDir.toString(), "backup.zip").toString()

    toJSON.convertirJson(personajes, rutaArchivoJson, rutaArchivoZip)
}

/**
 * Función en la que hacemos todas las consultas pedidas
 *
 * @param personajes lista de personajes
 */
fun procesarFichero(personajes: List<PersonajesDto>) {

    //El número de Personajes
    println(" --> Todos los Personajes (${personajes.size}):")
    personajes.forEach {println(it)}
    separacion()

    //Habilidad de un Personaje Específico
    val heroeRnd = (1..30).random()
    println(" --> habilidad del personaje con ID: $heroeRnd")
    val heroe = personajes.find { it.id == heroeRnd }
    println(heroe)
    separacion()


    //Listado de los personajes con ID par.
    println(" --> Listado de los personajes con ID par:")
    val personajePares = personajes.filter{ it.id % 2 == 0 }
    personajePares.forEach{ println(it) }
    separacion()


    //Personaje Más Viejo, Mostrando solo su nombre y edad.
    println(" --> Personaje Más Viejo")
    val personajeMasViejo = personajes.maxBy { it.edad }
    println("Nombre: ${personajeMasViejo.nombre}   Edad: ${personajeMasViejo.edad}")
    separacion()


    //Personaje Más Joven, Mostrando solo su nombre y edad.
    println(" --> Personaje Más Joven")
    val personajeMasJoven = personajes.minBy { it.edad }
    println("Nombre: ${personajeMasJoven.nombre}   Edad: ${personajeMasJoven.edad}")
    separacion()


    //Edad promedio de todos los Personajes
    println(" --> Edad promedio de todos los Personajes")
    val edadPromedio = personajes.sumBy { it.edad } / personajes.size
    println("Edad promedio: $edadPromedio")
    separacion()


    //Edad promedio de Personajes Vivos
    println(" --> Edad promedio de Personajes Vivos")
    val edadPromedioVivos = personajes.filter { it.vivo }.sumBy { it.edad } / personajes.filter { it.vivo }.size
    println("Edad promedio Vivos: $edadPromedioVivos")
    separacion()


    //Personajes que hayan fallecido.
    println(" --> Personajes que hayan fallecido:")
    //hacemos un filter
    val fallecidos = personajes.filter { !it.vivo }
    fallecidos.forEach{
        println(it)
    }
    println()
    //Edad promedio de Personajes Muertos
    println(" --> Edad promedio de Personajes Muertos")
    val edadPromedioMuertos = personajes.filter {!it.vivo }.sumBy { it.edad } / personajes.filter {!it.vivo }.size
    println("Edad promedio Muertos: $edadPromedioMuertos")
    separacion()


    //filtramos si son del tipo Héroe y cuantos son
    println(" --> Personajes que son Heroes (${personajes.count {!it.villano }}):")
    val heroes = personajes.filter { !it.villano }
    heroes.forEach {println("${it.nombre} alias ${it.nickName} es un Heroe")}
    separacion()
    //Héroes Vivos
    println(" --> Héroes Vivos")
    val heroesVivos = personajes.filter { it.vivo && !it.villano }
    heroesVivos.forEach { println(it) }
    separacion()


    //Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70.
    println(" --> Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70:")
    personajes.forEach { personaje ->
        if (personaje.edad < 40 && personaje.puntosCombate > 70) {
            println(personaje)
        }
    }
    separacion()


    //Agrupar Personajes por Habilidad
    println(" --> Agrupar Personajes por Habilidad:")
    personajes.groupBy { it.habilidades }.forEach { (habilidad, personajes) ->
        println("      ==> Habilidad: $habilidad (${personajes.size})")
        personajes.forEach {println(it)}
    }
    separacion()


    //filtramos si son del tipo villano y cuantos son
    println(" --> Personajes que son Villanos (${personajes.count { it.villano }}):")
    val villanos = personajes.filter { it.villano }
    villanos.forEach {println("${it.nombre} alias ${it.nickName} es un Villano")}
    separacion()
}

fun separacion(){
    repeat(80){print("═ ")}
    println()
}