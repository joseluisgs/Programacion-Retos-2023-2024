package org.example.repositories.superheroes

import org.example.models.Superheroe
import org.example.repositories.base.CrudRepository

/**
 * Clase que representa el repositorio de superhéroes de Marvel.
 *
 * @property superheroes Array de superhéroes de Marvel.
 * @property nextIndex Índice del siguiente superhéroe a agregar.
 */
class MarvelRepository : CrudRepository<Superheroe> {
    private var superheroes : Array<Superheroe?> = arrayOfNulls<Superheroe>(5)
    private var nextIndex = 0

    /**
     * Función que crea un nuevo superhéroe y lo agrega al repositorio.
     *
     * @param nombre Nombre real del superhéroe.
     * @param alias Alias o nombre en clave del superhéroe.
     * @param altura Altura del superhéroe en centímetros.
     * @param edad Edad del superhéroe en años.
     * @param notas Información adicional sobre el superhéroe.
     * @return superhéroe creado.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun crearSuperheroe(nombre: String, alias: String, altura: Int, edad: Int, notas: String): Superheroe {
        val superheroe = Superheroe(
            id = nextIndex + 1,
            nombre = nombre,
            alias = alias,
            altura = altura,
            edad = edad,
            notas = notas
        )
        if (nextIndex < superheroes.size) {
            superheroes[nextIndex++] = superheroe
        } else {
            println("Error: La base de datos está llena.")
        }
        return superheroe
    }

    /**
     * Función que obtiene todos los superhéroes del repositorio.
     *
     * @return Array de superhéroes.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun getSuperheroes(): Array<Superheroe> {
        for (superheroe in superheroes) {
            if (superheroe != null) {
                superheroes[nextIndex++] = superheroe
            }
        }
        return superheroes.filterNotNull().toTypedArray()
    }

    /**
     * Función que obtiene un superhéroe por su identificador.
     *
     * @param id Identificador único del superhéroe.
     * @return superhéroe buscado o null si no se encuentra.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun getSuperheroeById(id: Int): Superheroe? {
        for (superheroe in superheroes) {
            if (superheroe?.id == id) {
                return superheroe
            }
        }
        return null
    }

    /**
     * Función que guarda un superhéroe.
     *
     * @param value Superhéroe con la información actualizada.
     * @return superhéroe guardado.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun saveSuperheroe(value: Superheroe): Superheroe {
        if (superheroes.count { it == null } == 0) {
            superheroes = superheroes.copyOf(superheroes.size + 10)
        }
        val index = superheroes.indexOfFirst { it == null }
        nextIndex++
        value.id = nextIndex
        superheroes[index] = value
        return value
    }

    /**
     * Función que actualiza la información de un superhéroe en el repositorio.
     *
     * @param id Identificador único del superhéroe a actualizar.
     * @param value Superhéroe con la información actualizada.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun updateSuperheroe(id: Int, value: Superheroe): Superheroe? {
        for (i in 0 until nextIndex) {
            if (superheroes[i]?.id == id) {
                superheroes[i] = value.copy(updatedAt = System.currentTimeMillis())
            }
        }
        println("Error: Superhéroe no encontrado.")
        return value
    }

    /**
     * Función que elimina un superhéroe del repositorio.
     *
     * @param id Identificador único del superhéroe a eliminar.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    override fun deleteSuperheroe(id: Int): Superheroe? {
        val superheroe: Superheroe? = null
        for (i in 0 until nextIndex) {
            if (superheroes[i]?.id == id) {
                for (j in i until nextIndex - 1) {
                    superheroes[j] = superheroes[j + 1]
                }
                superheroes[nextIndex - 1] = null
                nextIndex--
            }
        }
        println("Error: Superhéroe no encontrado.")
        return superheroe
    }
}