package org.example

import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajesServiceImpl
import org.example.services.storage.StoragePersonajesCsv
import org.example.services.storage.StoragePersonajesJson
import org.example.validators.PersonajeValidator
import org.lighthousegames.logging.logging

private val logger = logging()

fun main() {
    val personajesServicio = PersonajesServiceImpl(
        storageCsv = StoragePersonajesCsv(),
        storageJson = StoragePersonajesJson(),
        personasRepository = PersonajesRepositoryImpl(),
        personasCache = PersonajesCache(),
        validator = PersonajeValidator()
    )

    // Sacamos listado de personajes desde el csv
    val listadoPersonajesCSV = personajesServicio.loadFromCsv()

    // Sacamos listado de personajes desde el json
    // val listadoPersonajesJSON = personajesServicio.loadFromJson()

    // Insertamos en la BBDD los personajes del listado
    listadoPersonajesCSV.forEach { personajesServicio.save(it) }

    // Mostramos los personajes cargados
    println("\nMOSTRANDO TODOS LOS PERSONAJES")
    personajesServicio.findAll().forEach { println(it) }

    // Buscando personaje por id
    println("\nMOSTRANDO PERSONAJE CON ID 2")
    println(personajesServicio.findById(2))

    // Buscando personajes por tipo
    println("\nMOSTRANDO TODOS LOS PERSONAJES DEL TIPO ENEMIGO")
    personajesServicio.findByTipo("Enemigo").forEach { println(it) }

    // Guardando personaje
    println("\nGUARDANDO PERSONAJE NUEVO")
    val p1 = Guerrero(99,"PersonajeExtra","PersonajeExtra",1,1,"PersonajeExtra")
    println(personajesServicio.save(p1))

    // Actualizando personaje
    println("\nACTUALIZANDO ÚLTIMO PERSONAJE AÑADIDO")
    val p2 = personajesServicio.findAll().maxBy { it.id }
    println("Imprimiendo personaje sin actualizar...")
    println(personajesServicio.findById(p2.id))
    val p3 = Guerrero(99,"PersonajeExtraUpdated","PersonajeExtraUpdated",2,2,"PersonajeExtraUpdated")
    println("Imprimiendo personaje actualizado...")
    println(personajesServicio.update(p2.id, p3))

    // Borrado lógico de personaje
    println("\nREALIZANDO BORRADO LÓGICO DEL ÚLTIMO PERSONAJE AÑADIDO")
    val p4 = personajesServicio.findAll().maxBy { it.id }
    println(personajesServicio.deleteLogico(p4.id))

    // Borrado físico de personaje
    println("\nREALIZANDO BORRADO FÍSICO DEL ÚLTIMO PERSONAJE AÑADIDO (Personaje_Extra_Updated)")
    println("Mostramos todos los personajes y este ya no está:")
    val p5 = personajesServicio.findAll().maxBy { it.id }
    personajesServicio.deleteFisico(p5.id)
    personajesServicio.findAll().forEach { println(it) }

    // Guardamos personajes en CSV de almacenamiento
    personajesServicio.storeToCsv(listadoPersonajesCSV)

    // Generamos personajes en JSON de almacenamiento
    personajesServicio.storeToJson(listadoPersonajesCSV)

    println("\n\nCONSULTAS:")
    println("==========")

    println("\nTres personajes más mayores:")
    listadoPersonajesCSV.sortedByDescending { it.edad }.take(3).forEach { println(it) }

    println("\nAgrupar personajes por tipo de arma:")
    listadoPersonajesCSV.groupBy { it.arma }.forEach { println(it) }

    println("\nPersonajes menores de edad:")
    listadoPersonajesCSV.filter { it.edad < 18 }.forEach { println(it) }

    println("\nPersonajes con más ataque: (primeros)")
    listadoPersonajesCSV.sortedByDescending { it.ataque }.take(3).forEach { println(it) }

    println("\nMostrar a todos los enemigos:")
    listadoPersonajesCSV.filterIsInstance<Enemigo>().forEach { println(it) }

    println("\nDos personajes con el ataque más fuerte:")
    listadoPersonajesCSV.sortedByDescending{ it.ataque }.take(2).forEach { println(it) }

}