package org.example.interfaces

import org.example.models.Heroe

/**
 * Interfaz donde se almacenan las función para imprimir por pantalla
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see imprimirArray
 * @see imprimirHeroe
 */
interface PrintFunctions {

    /**
     * Función que recibe un array de heroes y lo imprime por pantalla
     * @param arrayHeroes Array con todos los heroes
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun imprimirArray(arrayHeroes: Array<Heroe>) {
        for (heroe in arrayHeroes) {
            imprimirHeroe(heroe)
        }
    }

    /**
     * Función que recibe un objeto heroe y lo imprime
     * @param heroe objeto heroe
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun imprimirHeroe(heroe: Heroe?) {
        if (heroe == null) println("\nNo se encuentra un héroe con esa id ❌")
        else {
            println("\nID: ${heroe.id}")
            println("Nombre: ${heroe.nombre}")
            println("Alias: ${heroe.alias}")
            println("Altura: ${heroe.altura} cm")
            println("Edad: ${heroe.edad}")
            println("Notas: ${heroe.notas}")
            println("Hora de creación: ${heroe.createAt}")
            println("Hora de última modificación: ${heroe.updateAt}")
        }
    }
}