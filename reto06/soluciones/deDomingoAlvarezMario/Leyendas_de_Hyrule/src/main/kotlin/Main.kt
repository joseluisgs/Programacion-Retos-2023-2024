import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.cache.personajes.PersonajesCache
import services.storage.StoragePersonajesCsv

fun main(args: Array<String>) {
    val personasService = PersonajesServiceImpl(
        storageCsv = StoragePersonajesCsv(),
        //storageJson = StoragePersonajesJson(),
        personasRepository = PersonajesRepositoryImpl(),
        personasCache = PersonajesCache()
    )

    val personas = personasService.loadFromCsv()
    personas.forEach {
        // insertamos en la base de datos
        personasService.save(it)
    }

    // Mostramos los datos
    personasService.findAll().forEach {
        println(it)
    }
}
