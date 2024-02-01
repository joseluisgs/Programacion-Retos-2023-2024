package org.example.repository

import org.example.models.Heroe
/**
 * Clase que representa el universo de DC Comics.
 */
class DC {
    // Array que contiene a los héroes del universo de DC Comics.
    private var heroes = arrayOf(
        Heroe("Bruce Wayne", "Batman", 180, 30, "Protector de Gotham", 1, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Clark Kent", "Superman", 190, 30, "Protector de la Tierra", 2, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Diana Prince", "Wonder Woman", 180, 200, "Guerrera de Themyscira", 3, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Barry Allen", "Flash", 180, 30, "Corredor escarlata", 4, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Hal Jordan", "Green Lantern", 185, 40, "Lanterna verde", 5, System.currentTimeMillis(), System.currentTimeMillis())
    )

    // Variable que representa el ID que se asignará al próximo héroe que se agregue.
    private var nextId = 6

    /**
     * Retorna un arreglo de todos los héroes en el universo de DC Comics.
     * @return Un arreglo de objetos Heroe.
     */
    fun getHeroes(): Array<Heroe> {
        return heroes
    }

    /**
     * Agrega un nuevo héroe al universo de DC Comics.
     * @param heroe El héroe que se va a agregar.
     * @return El héroe que se agregó, con su ID actualizado.
     */
    fun addHeroe(heroe: Heroe): Heroe {
        val newHeroe = heroe.copy(id = nextId++)
        heroes = heroes.plus(newHeroe)
        return newHeroe
    }

    /**
     * Elimina un héroe del universo de DC Comics.
     * @param id El ID del héroe que se va a eliminar.
     * @return true si se eliminó el héroe correctamente, false si no se encontró el héroe.
     */
    fun deleteHeroe(id: Int): Boolean {
        val index = findHeroIndex(id)
        if (index == null) {
            return false
        }
        heroes = heroes.filterNot { it.id == id }.toTypedArray()
        return true
    }

    /**
     * Encuentra el índice de un héroe en el arreglo de héroes.
     * @param id El ID del héroe que se está buscando.
     * @return El índice del héroe, o null si el héroe no se encuentra en el arreglo.
     */
    fun findHeroIndex(id: Int): Int? {
        return heroes.indexOfFirst { it.id == id }
    }

    /**
     * Muestra la lista de héroes del universo de DC Comics en la consola.
     */
    fun viewHeroes() {
        println("\nLista de héroes del universo de DC Comics:")
        heroes.forEach { heroe ->
            println("ID: ${heroe.id} - Nombre: ${heroe.nombre} - Alias: ${heroe.alias}")
        }
    }

    /**
     * Muestra un menú interactivo para interactuar con la Liga de la Justicia y realizar operaciones con los héroes.
     */
    fun menu() {
        while (true) {
            println("Bienvenido a la Liga de la Justicia... ¿Qué desea hacer?")
            println("1. Añadir héroe")
            println("2. Ver lista de héroes")
            println("3. Modificar héroe")
            println("4. Eliminar héroe")
            println("5. Salir")

            print("Seleccione una opción: ")
            val option = readLine()?.toIntOrNull()
            println("")

            when (option) {
                1 -> {
                    print("Nombre del héroe: ")
                    val nombre = readLine()
                    print("Alias del héroe: ")
                    val alias = readLine()
                    addHeroe(Heroe(nombre ?: "", alias ?: "", 0, 0, "", 0, 0, 0))
                }
                2 -> viewHeroes()
                3 -> {
                    print("Índice del héroe a modificar: ")
                    val index = readLine()?.toIntOrNull()
                    if (index != null) {
                        print("Nuevo nombre del héroe: ")
                        val newName = readLine()
                        print("Nuevo alias del héroe: ")
                        val newAlias = readLine()
                        if (newName != null && newAlias != null) {
                            modifyHeroe(index, Heroe(newName, newAlias, 0, 0, "", 0, 0, 0))
                        }
                    } else {
                        println("Índice inválido.")
                    }
                }
                4 -> {
                    print("Índice del héroe a eliminar: ")
                    val index = readLine()?.toIntOrNull()
                    if (index != null) {
                        deleteHeroe(index)
                    } else {
                        println("Índice inválido.")
                    }
                }
                5 -> return
                else -> println("Opción inválida.")
            }
            println("")

        }
    }

    /**
     * Modifica los detalles de un héroe en el universo de DC Comics.
     * @param index El índice del héroe que se va a modificar en el arreglo.
     * @param newHeroe El nuevo héroe con los detalles actualizados.
     */
    fun modifyHeroe(index: Int, newHeroe: Heroe) {
        if (index in heroes.indices) {
            heroes[index] = newHeroe
        } else {
            println("Índice inválido.")
        }
    }
}