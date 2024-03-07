import service.MarvelService
import service.backup.Backup
import service.backup.BackupImpl
import storage.PersonajeStorage
import java.io.File

fun main(args: Array<String>) {
    val file= File("src/main/resources/personajes.csv")

    val backup=BackupImpl()
    backup.restore()
    backup.backup()
    val service=MarvelService(
        storage = PersonajeStorage()
    )
    service.simulacion()

    val lista=service.leer()
    println("CONSULTAS")
    println("Habilidad de un Personaje Específico:\nIntroduce el id de un personaje")
    val id= readln().toIntOrNull()?:0
    val personajeEspecifico=lista.filter { it.id==id }.map { it.habilidad }
    println(personajeEspecifico)

    println()
    println("Listado de los personajes con ID par")
    val listaPar=lista.filter { it.id % 2== 0 }
    listaPar.forEach { println(it) }

    println()
    println("Personaje Más Viejo:")
    lista.maxBy { it.edad }.also {
        println("${it.edad}-${it.nombre}")
    }

    println()
    println("Personaje Más Joven")
    val masJoven=lista.minBy { it.edad }
    println(masJoven)

    println()
    println("Personajes que hayan fallecido")
    val listaMuertos=lista.filter { !it.vivo }
    listaMuertos.forEach { println(it) }

    println()
    println("Villanos en la Base de Datos:")
    val villanos=lista.filter { it.villano }
    villanos.forEach { println(it) }

    println()
    println("Héroes Vivos:")
    val vivos=lista.filter { it.vivo }
    vivos.forEach { println(it) }

    println()
    println("Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70")
    val mayores40ycombate70=lista.filter { it.edad<40 && it.puntosCombate>40 }
    mayores40ycombate70.forEach { println(it) }

    println()
    println("Personajes que no son no Héroes:")
    val noHeroes=lista.filter { it.villano }
    noHeroes.forEach { println(it) }

    println()
    println("Agrupar Personajes por Habilidad:")
    lista.groupBy { it.habilidad }.forEach { (habilidad,lista)->
        val numero= lista.count()
        println("$habilidad : $numero")
    }

}