import Services.cache.PersonajesCache
import Services.personajes.PersonajesServiceImpl
import Services.Storage.StoragePersonajeJSON
import Services.Storage.StoragePersonajesCSV
import models.Personaje
import java.io.File

fun main(args: Array<String>) {
    val fichero = ClassLoader.getSystemResource("personajes.csv").file
    val personajes= leerFichero(File(fichero))

    val servicio= PersonajesServiceImpl(
        StoragePersonajesCSV(),
        StoragePersonajeJSON(),
        PersonajesRepositoryImpl(),
        PersonajesCache()
    )
    servicio.storeToCsv(personajes)
    servicio.storeToJson(personajes)
    personajes.forEach { servicio.save(it) }

    val personajesJson= servicio.loadFromJson("personajes-back.json")
    println()


    println("Tres personajes más mayores:")
    personajesJson.sortedByDescending { it.edad }.take(3).forEach { println(it) }
    println()

    println("Agrupar personajes por tipo de arma:")
    personajesJson.groupBy { it.arma }.forEach { println(it) }

    println()
    println("Personajes menores de edad:")
    personajesJson.filter { it.edad < 18 }

    println()
    println("Personajes con más ataque:")
    personajesJson.sortedByDescending { it.ataque }.first()

    println()
    println("Mostrar a todos los enemigos:")
    personajesJson.filterIsInstance<Enemigo>().forEach { println(it) }

    println()
    println("Dos personajes con el ataque más fuerte:")
    personajesJson.sortedByDescending{ it.ataque }.take(2).forEach { println(it) }

}

fun leerFichero(file: File): List<Personaje> {
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
