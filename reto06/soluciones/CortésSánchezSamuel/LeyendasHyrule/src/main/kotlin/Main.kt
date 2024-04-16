import models.Enemigo
import models.Personaje
import repositories.personajes.PersonajeRepositoryImpl
import services.cache.PersonajesCache
import services.personajes.PersonajesServiceImpl
import services.storage.StoragePersonajeCsv
import services.storage.StoragePersonasJson
import java.io.File

fun main() {
    val fichero = ClassLoader.getSystemResource("personajes.csv").file
    val personajes= procesarFichero(File(fichero))

    val servicio = PersonajesServiceImpl(
        StoragePersonajeCsv(),
        StoragePersonasJson(),
        PersonajeRepositoryImpl(),
        PersonajesCache()
    )

    servicio.storeToCsv(personajes)
    servicio.storeToJson(personajes)
    personajes.forEach { servicio.save(it) }

    val personajesJson = servicio.loadFromJson("personajes-back.json")
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

fun procesarFichero(file: File) : List<Personaje>{
    return file.readLines().drop(1).map {
        val parts = it.split(",")
        Personaje(
            nombre = parts[0],
            habilidad = parts[1],
            ataque = parts[2].toInt(),
            edad = parts[3].toInt(),
            arma = parts[4]
        )
    }
}

