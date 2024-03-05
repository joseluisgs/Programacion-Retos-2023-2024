package org.example
import org.example.service.PersonajeService
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Hola Personajes!")

    val personajeService = PersonajeService()

    val personajes = personajeService.obtenerPersonajes()

    // 1. Habilidad de un Personaje Específico
    val habilidad = personajes.find { it.id == 1 }?.habilidad
    println("Habilidad del personaje con ID 1: $habilidad")

    // 2. Listado de los personajes con ID par
    val listaPersonajesPares = personajes.filter { it.id % 2 == 0 }
    println("Personajes con ID par:")
    listaPersonajesPares.forEach { println(it) }

    // 3. Personaje Más Viejo
    val personajeMayor = personajes.maxByOrNull { it.edad }
    println("Personaje más viejo: $personajeMayor")

    // 4. Personaje Más Joven
    val personajeJoven = personajes.minByOrNull { it.edad }
    println("Personaje más joven: $personajeJoven")

    // 5. Personajes que hayan fallecido
    val personajesMuertos = personajes.filter { !it.vivo }
    println("Personajes fallecidos:")
    personajesMuertos.forEach { println(it) }

    // 6. Villanos en la Base de Datos
    val personajesVillanos = personajes.filter { it.villano }
    println("Personajes villanos:")
    personajesVillanos.forEach { println(it) }

    // 7. Héroes Vivos
    val heroesVivos = personajes.filter { it.vivo && !it.villano }
    println("Héroes vivos:")
    heroesVivos.forEach { println(it) }

    // 8. Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70
    val personajesEdadPc = personajes.filter { it.edad < 40 && it.puntosCombate > 70 }
    println("Personajes con edad menor a 40 y puntos de combate mayor a 70:")
    personajesEdadPc.forEach { println(it) }

    // 9. Igual a 6 (Villanos en la Base de Datos)

    // 10. Agrupar Personajes por Habilidad
    val agruparPorHabilidad = personajes.groupingBy { it.habilidad }.eachCount()
    println("Personajes agrupados por habilidad:")
    agruparPorHabilidad.forEach { (habilidad, cantidad) -> println("$habilidad: $cantidad") }

    // 11. Personajes Aleatorios
    val personajesAleatorios = personajes.filter { it.vivo }.shuffled().take(5)
    println("Personajes aleatorios:")
    personajesAleatorios.forEach { println(it) }

    /* val jsonPersonajes = Json.encodeToString(personajes)
    val jsonFile = File("personajes.json")
    jsonFile.writeText(jsonPersonajes)
    println("Datos guardados en personajes.json")

    // Realizar un backup de los datos
    val backupService = BackupImpl()
    backupService.backup()
    println("Backup realizado con éxito")*/

    // Determinación del Ganador
    val ganador = personajeService.determinarGanador()
    println(ganador)
}