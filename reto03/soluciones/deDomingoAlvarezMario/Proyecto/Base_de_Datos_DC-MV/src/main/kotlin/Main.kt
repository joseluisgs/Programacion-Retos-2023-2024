package org.example

import org.example.models.Dc
import org.example.models.Marvel
import java.util.*

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 */
val dc = Dc()
val marvel = Marvel()
var intentos = 3

/**
 * iniciamos y
 */
fun main() {
    println()
    println("""
        ╔══════════════════╗
        ║ INICIO DE SESIÓN ║
        ╚══════════════════╝""".trimIndent())
    opciones()
}

/**
 * iniciamos la base de datos correspondiente o lanzamos error
 */
fun opciones(){
    println()
    print("  --> Clave de Acceso: ")
    when(readln().uppercase(Locale.getDefault())){
        "DC" -> dc.simulador()
        "MARVEL" -> marvel.simulador()
        else -> incorrectOption()
    }
}

/**
 * Lanzamos error y volvemos a preguntar la contraseña
 */
fun incorrectOption() {
    if (intentos > 0) {
        println("Contraseña incorrecta. Inténtelo de nuevo. Intentos restantes: $intentos")
        opciones()
        intentos--
    }else {
        println("Has introducido demasiadas veces incorrectamente la contraseña.")
    }
}
