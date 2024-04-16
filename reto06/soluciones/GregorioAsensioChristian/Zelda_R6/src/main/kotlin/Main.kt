package org.example

import org.example.models.Enemigo
import org.example.models.Guerrero

fun main() {
    val personajesService = PersonajesServiceImpl(
        storageCsv = StoragePersonajesCsv(),
        storageJson = StoragePersonajesJson(),
        personajesRepository = PersonajesRepositoryImpl(),
        personajesCache = PersonajesCache()
    )


    val listadoPersonajesCSV = personajesServicio.loadFromCsv()

    listadoPersonajesCSV.forEach { personajesServicio.save(it) }

    println("\nMOSTRANDO TODOS LOS PERSONAJES")
    personajesServicio.findAll().forEach { println(it) }
    
    println("\nMOSTRANDO PERSONAJE CON ID 4")
    println(personajesServicio.findById(4))

    // Buscando personajes por tipo
    println("\nMOSTRANDO TODOS LOS PERSONAJES DEL TIPO ENEMIGO")
    personajesServicio.findByTipo("Enemigo").forEach { println(it) }

    println("\nGUARDANDO PERSONAJE NUEVO")
    val p1 = Guerrero(99,"PersonajeExtra","PersonajeExtra",1,1,"PersonajeExtra")
    println(personajesServicio.save(p1))

    println("\nACTUALIZANDO ÚLTIMO PERSONAJE AÑADIDO")
    val p2 = personajesServicio.findAll().maxBy { it.id }
    println("Imprimiendo personaje sin actualizar...")
    println(personajesServicio.findById(p2.id))
    val p3 = Guerrero(99,"PersonajeExtraUpdated","PersonajeExtraUpdated",2,2,"PersonajeExtraUpdated")
    println("Imprimiendo personaje actualizado...")
    println(personajesServicio.update(p2.id, p3))


    // CSV
    personajesServicio.storeToCsv(listadoPersonajesCSV)
    // JSON
    personajesServicio.storeToJson(listadoPersonajesCSV)


    listadoPersonajesCSV.sortedByDescending { it.edad }.take(3).forEach { println(it) }
    listadoPersonajesCSV.groupBy { it.arma }.forEach { println(it) }

    listadoPersonajesCSV.filter { it.edad < 18 }.forEach { println(it) }
    listadoPersonajesCSV.sortedByDescending { it.ataque }.take(3).forEach { println(it) }

    listadoPersonajesCSV.sortedByDescending{ it.ataque }.take(2).forEach { println(it) }

    println("\nMostrar a todos los enemigos:")
    listadoPersonajesCSV.filterIsInstance<Enemigo>().forEach { println(it) }

}
}