package org.example.Controllers

import Heroe


/**
 * @author Mario de Domingo
 * @property dueño
 *
 * Clase abstracta de la que eredan DC y Marvel para usar su propia base de datos
 */
abstract class BaseDeDatos(private val dueño: String) {
    private var tamArray = 5
    var baseDeDatos = Array<Heroe?>(tamArray) { null }
    private val heroe = Heroe()

    /**
     * Función princimal para inicializar la base de Datos
     */
    fun simulador() {
        personasIniciales()
        saludar()
        print("Cargando")
        repeat(10) {
            print(".")
            Thread.sleep(250)
        }
        println()
        imprimirArray()
        menuAccion()
    }

    /**
     * Saluda al dueño de la base de Datos secreta
     */
    open fun saludar(){
        println()
        println("¡¡¡Bienvenido!!! $dueño")
        println()
    }

    /**
     * Mostramos las funciones disponibles y llamamos al la función para pa opción
     */
    private fun menuAccion() {
        println()
        println(
            """Seleccione que desea Hacer ahora:
            |  ╔════════════════════╗
            |  ║ 1. Añadir Héroe    ║
            |  ║ 2. Eliminar Héroe  ║
            |  ║ 3. Mostrar listado ║
            |  ║ 4. Cerrar sesión   ║
            |  ╚════════════════════╝
        """.trimMargin()
        )
        opcionesBaseDeDatos()
    }

    /**
     * Elegimos la opcion y nos lleva a su funcion correspondiente
     */
    private fun opcionesBaseDeDatos() {
        countnulls()
        print("       -> Opción: ")
        when (readln().toIntOrNull()) {
            1 -> asignarHeroes()
            2 -> eliminarHeroe()
            3 -> {
                imprimirArray()
                menuAccion()
            }

            4 -> cerrarSesion()
            else -> {
                println("Opción no valida, Seleccione de nuevo")
                menuAccion()
            }
        }
    }

    /**
     * @return null
     * borramos el heroe de la casilla que queramos
     */
    private fun eliminarHeroe() {
        println()
        println("Elige la ID del personaje que deseas eliminar")
        print(" --> ID: ")
        val id = readln().toIntOrNull()
        if (id != null) {
            if (baseDeDatos[id-1] != null) {
                baseDeDatos[id-1] = null
                println("Se ha eliminado el Héroe con ID: $id")
            } else {
                println("ID no válida o Héroe no encontrado")
            }
        }
        menuAccion()
    }

    /**
     * @return héroe
     * elegimos cuantos heroes agregar y vamos uno a uno asignado sus valores
     */
    private fun asignarHeroes() {
        print("Cuantos heroes Quiere añadir: ")
        val heroes = readln().toIntOrNull()
        if (heroes != null && heroes > 0) {
            repeat(heroes) {
                val nuevo = Heroe()
                nuevo.asignar()
                var i = 0
                var asignado = false
                do {
                    if (baseDeDatos[i] == null) {
                        baseDeDatos[i] = nuevo
                        asignado = true
                        println("Heroe añadido con ID: $i")
                        println()
                        countnulls()
                    } else {
                        i++
                    }
                } while (!asignado)
            }
        } else {
            println("Cantidad no válida. Ingrese un número mayor a cero.")
        }
        menuAccion()
    }

    /**
     * @return fin
     * finalizamos el programa y nos despedimos del Dueño
     */
    private fun cerrarSesion() {
        println()
        println("¡¡¡Hasta pronto!!! $dueño")
    }
    open fun personasIniciales() {}

    /**
     * @return print Matriz
     */
    private fun imprimirArray() {
        println("═════════════════════════════════════════════════════════════ ")
        println("Este es el Listado de SuperHeroes que forman parte de MARVEL:")
        for ((index, heroe) in baseDeDatos.withIndex()) {
            if (heroe != null) {
                println(
                    """  - ${heroe.nombre}
                    |      * ID: ${index+1}
                    |      * Alias: ${heroe.apodo}
                    |      * Edad: ${heroe.edad} años
                    |      * Altura: ${heroe.altura} cm""".trimMargin()
                )
                Thread.sleep(400)
            }else {
                println("  - Futuro Heroe...")
            }
        }
        println("═════════════════════════════════════════════════════════════")
    }

    /**
     * @return nulls
     * contamos los nulos para saber si la matriz necesita que añadamos más casillas o menos
     */
    private fun countnulls() {
        var nulls = 0
        for (i in baseDeDatos.indices) {
            if (baseDeDatos[i] == null) {
                nulls++
            }
        }
        if (nulls == 0) {
            modifyArrayMas5()
        } else if (nulls == 6) {
            modifyArrayMenos5()
        }
    }

    /**
     * @return tamArray
     * minimizamos el array
     */
    private fun modifyArrayMenos5() {
        tamArray -= 5
        baseDeDatos = baseDeDatos.copyOf(tamArray)
    }

    /**
     * @return tamArray
     * maximizamos el array
     */
    private fun modifyArrayMas5() {
        baseDeDatos = baseDeDatos.copyOf(tamArray + 5)
        tamArray += 5
    }
}