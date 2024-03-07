package org.example

import org.example.models.Heroe
import org.example.services.backup.BackupImpl
import org.example.services.heroe.HeroeServiceImpl
import org.example.services.heroe.HeroesService
import org.example.services.storage.bitacora.BitacoraFileStorageTxt
import org.example.services.storage.heroe.HeroeFileStorageCSV
import org.example.services.storage.heroe.HeroeFileStorageJson
import org.lighthousegames.logging.logging
import java.time.LocalDate

/**
 * Batalla de Héroes Marvel
 *
 * @author Germán Fernández Carracedo - 1º DAW IES Luis Vives Leganés
 * @since 1.0
 */

private val log = logging()

val service:HeroesService = HeroeServiceImpl(
    storageCsv = HeroeFileStorageCSV(),
    bitacora= BitacoraFileStorageTxt(),
    storageJson =HeroeFileStorageJson(),
    backup = BackupImpl()
)

val listPlayer = mutableListOf<Heroe>()
val listComputer = mutableListOf<Heroe>()

fun main() {

    // Menú principal del programa
    do {
        println("Batalla Marvel")
        println("==============")
        mostrarMenuOperaciones()

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1 -> mostrarSubMenuConsulta()
            2 -> mostrarSubMenuSeleccionAleatoria()
            3 -> mostrarSubMenuBatalla()
            4 -> mostrarSubMenuBackup()
        }
    } while (opcion!= 0)
    println("¡Vuelva pronto!")


}

/**
 * Muestra submenú de opciones
 */
fun mostrarSubMenuBackup() {

    do {
        println("Copia de Seguridad")
        println("=================")
        println("1. Backup de CSV pasando por JSON")
        println("2. Restaurar copia de zip a CSV")
        println()
        println("0. Volver ")
        println()
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> backupCsv()
            2-> restoreCsv()
        }
    } while (opcion!= 0)

}


/**
 * Desarrolla la batalla entre las dos listas de héroes, la del jugador y la de la máquina
 */
fun mostrarSubMenuBatalla() {

    var puntosPlayer:Int = 0
    var puntosComputer:Int = 0

    println()

    for (i in 0..4){
        print("Jugador [${i+1}] PC=${listPlayer[i].puntosCombate} | Máquina [${i+1}] PC=${listComputer[i].puntosCombate}")
        if(listPlayer[i].puntosCombate > listComputer[i].puntosCombate){
            println("\t gana el jugador (${listPlayer[i].nickName} - ${listComputer[i].nickName})")

            puntosPlayer++}
        else if (listPlayer[i].puntosCombate < listComputer[i].puntosCombate) {
            println("\t gana la máquina (${listPlayer[i].nickName} - ${listComputer[i].nickName})")
            puntosComputer++
        }
        else println("\t empate")
        // si no, empate y no suma ninguno
    }

    println()

    var textoBitacora:String

    if (puntosPlayer > puntosComputer) {
        println("¡¡ Enhorabuena !! ¡¡ Has ganado la batalla !!")
        textoBitacora = "Fecha: ${LocalDate.now()}\tPuntos Jugador: $puntosPlayer\tPuntos Máquina: $puntosComputer\tGanador: Jugador"
    } else if (puntosPlayer < puntosComputer) {
        println("¡¡ Vaya, has perdido la batalla !!")
        textoBitacora = "Fecha: ${LocalDate.now()}\tPuntos Jugador: $puntosPlayer\tPuntos Máquina: $puntosComputer\tGanador: Máquina"
    } else { // empate
        println("¡¡ Fuerzas igualadas !! ¡¡ Empate!!")
        textoBitacora = "Fecha: ${LocalDate.now()}\tPuntos Jugador: $puntosPlayer\tPuntos Máquina: $puntosComputer\tGanador: Empate"
    }
    service.anadirTextoBitacora(textoBitacora)
    println(textoBitacora)

}

/**
 * Genera y muestra las listas de personajes del jugador y de la máquina
 */
fun mostrarSubMenuSeleccionAleatoria() {

    println("Selección Aleatoria de Personajes")
    println("=================================")

    val listaAux = service.getAll().filter { it.vivo }.toMutableList()

    listPlayer.clear()
    listComputer.clear()

    // rellenamos la lista del jugador
    repeat(5){
        listPlayer.add(listaAux.random())
        // eliminamos el elemento seleccionado de la lista inicial para que no podamos volver a seleccionarlo
        listaAux.removeIf() { it.id == listPlayer.last().id }

            //log.debug { "Lista Aux. size = ${listaAux.size}" }
    }

    // rellenamos la lista de la máquina
    repeat(5){
        listComputer.add(listaAux.random())
        // eliminamos el elemento seleccionado de la lista inicial para que no podamos volver a seleccionarlo
        listaAux.removeIf { it == listComputer.last() }
            //log.debug { "Lista Aux. size = ${listaAux.size}" }
    }

    println("Lista Jugador: ")
    listPlayer.forEach { println(it) }
    println("Lista Máquina: ")
    listComputer.forEach { println(it) }

}

/**
 * Mostramos opciones del menú principal
 */
private fun mostrarMenuOperaciones() {
    println("1. Consulta de Datos")
    println("2. Selección Aleatoria de Personajes")
    println("3. Batalla y Determinación del Ganador")
    println("4. Backup")
    println()
    println("0. Salir")
    println()
    print("\nSeleccione opción: ")
}

/**
 * Mostramos opciones del menú de consulta de datos
 */
fun mostrarSubMenuConsulta() {
    do {
        println("Consulta de Datos")
        println("=================")
        println("1.1. Leer Personajes de CSV")
        println("2.2. Mostrar resultados consultas")
        println()
        println("0. Volver ")
        println()
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> leerPersonajesDeCSV()
            2-> mostrarResultadosConsultas()
        }
    } while (opcion!= 0)
}

/**
 * Opción del menú que llama a restaurar el fichero CSV y mostrar la lista de personajes una vez restaurada
 */
fun restoreCsv() {
    service.restore()
    service.getAll().forEach{ println(it)}

}

/**
 * Opción del menú que llama a backup del fichero CSV de personajes
 */
fun backupCsv() {
    service.backup()
}

/**
 * Opción del menú que muestra todos los personajes de la lista
 */
fun leerPersonajesDeCSV() {
    println("La lista de los personajes es:")
    service.getAll().forEach { println(it) }

}
/**
 * Opción del menú que muestra todos los personajes de la lista
 */
fun mostrarResultadosConsultas() {

    println("Listado de los personajes con ID par:")
    service.getAll().filter { it.id % 2 == 0 }.forEach {  println(it) }
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Personaje Más Viejo (solo nombre y edad)")
    println(service.getAll().maxBy { it.edad }.let {println("Nombre: ${it.nombre}, Edad: ${it.edad}")})
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Personaje Más Joven")
    println(service.getAll().minBy { it.edad }.let{println("Nombre: ${it.nombre}, Edad: ${it.edad}")})
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Personajes que hayan fallecido")
    service.getAll().filter { !it.vivo }.forEach{ println(it) }
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Villanos en la Base de Datos")//: Filtra y muestra la lista de todos los personajes que son villanos.
    service.getAll().filter { it.villano }.forEach { println(it) }
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Héroes Vivos:")// Encuentra y muestra la lista de todos los héroes que están actualmente vivos.
    service.getAll().filter{ it.vivo }.forEach { println(it) }
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70")
    service.getAll().filter { (it.edad < 40) && (it.puntosCombate > 70) }.forEach { println(it) }
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Personajes que no son Héroes (Villanos)")
    service.getAll().filter { it.villano }.sortedBy { it.nickName }.forEach{ println(it)}
    println("Pulse Intro para siguiente consulta")
    readln()

    println("Agrupar Personajes por Habilidad")//: Realiza un conteo de cuántos personajes tienen cada habilidad y muestra los resultados.
    service.getAll().groupBy { it.habilidad }.forEach{println("${it.key} -> ${it.value.size}")}
}



