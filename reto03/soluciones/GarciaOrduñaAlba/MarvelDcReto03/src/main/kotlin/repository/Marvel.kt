package org.example.repository
import org.example.models.Heroe
/**
 * Clase que representa el universo de Marvel Comics.
 */
class Marvel {

    /**
     * Array que contiene a los héroes del universo de Marvel Comics.
     */
    private var heroes = arrayOf(
        Heroe("Tony Stark", "Ironman", 180, 45, "Héroe de hierro", 1, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Peter Parker", "Spiderman", 175, 25, "Araña de la ciudad", 2, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Odinson", "Thor", 200, 1000, "Dios del trueno", 3, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Bruce Banner", "Hulk", 200, 40, "Héroe de la fuerza", 4, System.currentTimeMillis(), System.currentTimeMillis()),
        Heroe("Steve Rogers", "Capitán América", 185, 100, "Líder de los Vengadores", 5, System.currentTimeMillis(), System.currentTimeMillis())
    )

    /**
     * El ID que se asignará al próximo héroe que se agregue.
     */
    private var nextId = 6

    /**
     * Agrega un nuevo héroe al universo de Marvel Comics.
     * @param heroe El héroe que se va a agregar.
     * @return El héroe que se agregó, con su ID actualizado.
     */
    fun addHero(heroe: Heroe): Heroe {
        val newHeroe = heroe.copy(id = nextId++)
        val newHeroes = Array(heroes.size + 1) { i ->
            if (i < heroes.size) {
                heroes[i]
            } else {
                newHeroe
            }
        }
        heroes = newHeroes
        return newHeroe
    }

    /**
     * Retorna un arreglo de todos los héroes en el universo de Marvel Comics.
     * @return Un arreglo de objetos Heroe.
     */
    fun getAllHeroes(): Array<Heroe> {
        return heroes
    }

    /**
     * Obtiene un héroe del universo de Marvel Comics por su ID.
     * @param id El ID del héroe que se busca.
     * @return El héroe correspondiente al ID, o null si no se encuentra.
     */
    fun getHeroById(id: Int): Int {
        val index = heroes.indexOfFirst { it.id == id }
        return if (index != -1) {
            index
        } else {
            -1
        }
    }

    /**
     * Actualiza la información de un héroe existente en el universo de Marvel Comics.
     * @param heroe El héroe con la información actualizada.
     * @return El héroe actualizado, o null si no se encontró al héroe.
     */
    fun updateHero(index: Int, heroe: Heroe): Heroe? {
        if (index in heroes.indices) {
            heroes[index] = heroe
            return heroes[index]
        }
        return null
    }

    /**
     * Elimina un héroe del universo de Marvel Comics.
     * @param id El ID del héroe que se va a eliminar.
     * @return true si se eliminó el héroe correctamente, false si no se encontró el héroe.
     */
    fun deleteHero(id: Int): Boolean {
        val index = heroes.indexOfFirst { it.id == id }
        if (index != -1) {
            val newHeroes = Array(heroes.size - 1) { i ->
                if (i < index) {
                    heroes[i]
                } else {
                    heroes[i + 1]
                }
            }
            heroes = newHeroes
            return true
        }
        return false
    }

    /**
     * Función que muestra la lista de héroes en la consola.
     */
    fun viewHeroes() {
        println("Lista de héroes:")
        heroes.forEach { heroe ->
            println("\"ID: ${heroe.id} -${heroe.nombre} - ${heroe.alias}")
        }
    }

    /**
     * Función que modifica un héroe en la lista de héroes.
     * @param index Índice del héroe que se va a modificar en el arreglo.
     * @param newHero Nuevo héroe con los detalles actualizados.
     */
    fun modifyHero(index: Int, newHero: Heroe) {
        if (index in heroes.indices) {
            heroes[index] = newHero
        } else {
            println("Índice inválido.")
        }
    }

    /**
     * Función que elimina un héroe de la lista de héroes.
     * @param index Índice del héroe que se va a eliminar del arreglo.
     */
    fun removeHero(index: Int) {
        if (index in heroes.indices) {
            val newHeroes = Array(heroes.size - 1) { i ->
                if (i < index) {
                    heroes[i]
                } else {
                    heroes[i + 1]
                }
            }
            heroes = newHeroes
        } else {
            println("Índice inválido.")
        }
    }

    /**
     * Función que muestra un menú interactivo para interactuar con Los Vengadores y realizar operaciones con los héroes.
     */
    fun menu() {
        while (true) {
            println("Bienvenido al menú de Los Vengadores: ¿Qué desea hacer?")
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
                    val nombre = readLine() ?: ""
                    print("Alias del héroe: ")
                    val alias = readLine() ?: ""
                    print("Altura del héroe: ")
                    val altura = readLine()?.toIntOrNull() ?: 0
                    print("Edad del héroe: ")
                    val edad = readLine()?.toIntOrNull() ?: 0
                    print("Notas del héroe: ")
                    val notas = readLine() ?: ""
                    print("Id del héroe: ")
                    val id = readLine()?.toIntOrNull() ?: 0

                    // Crear una instancia de Heroe con los datos proporcionados
                    val nuevoHeroe = Heroe(nombre, alias, altura, edad, notas, id)

                    // Llamar a la función addHero con el héroe creado
                    addHero(nuevoHeroe)
                }
                2 -> viewHeroes()
                3 -> {
                    print("Índice del héroe a modificar: ")
                    val index = readLine()?.toIntOrNull()
                    if (index != null) {
                        print("Nuevo nombre del héroe: ")
                        val newName = readLine()
                        print("Nuevo alias del héroe: ")
                        val newPower = readLine()
                        if (newName != null && newPower != null) {
                            modifyHero(index, Heroe(newName, newPower, 0, 0, "", 0, 0, 0))
                        }
                    } else {
                        println("Índice inválido.")
                    }
                }
                4 -> {
                    print("Índice del héroe a eliminar: ")
                    val index = readLine()?.toIntOrNull()
                    if (index != null) {
                        removeHero(index)
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

}