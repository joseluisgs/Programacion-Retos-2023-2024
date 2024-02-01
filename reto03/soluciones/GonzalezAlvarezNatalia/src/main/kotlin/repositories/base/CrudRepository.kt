package org.example.repositories.base

/**
 * Interfaz que representa las operaciones CRUD de los superhéroes.
 *
 * @property T tipo de superhéroes que maneja este repositorio.
 */
interface CrudRepository<T> {
    /**
     * Función que crea un nuevo superhéroe.
     *
     * @property nombre Nombre real del superhéroe.
     * @property alias Alias o nombre en clave del superhéroe.
     * @property altura Altura del superhéroe en centímetros.
     * @property edad Edad del superhéroe en años.
     * @property notas Información adicional sobre el superhéroe.
     * @return superhéroe creado.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun crearSuperheroe(nombre: String, alias: String, altura: Int, edad: Int, notas: String): T

    /**
     * Función que muestra todos los superhéroes del repositorio.
     *
     * @return array de todos los superhéroes en el repositorio.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun getSuperheroes(): Array<T>

    /**
     * Función que muestra un superhéroe por su ID.
     *
     * @param id ID del superhéroe a mostrar.
     * @return superhéroe con el ID proporcionado, o null si no existe.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun getSuperheroeById(id: Int): T?

    /**
     * Función que guarda un superhéroe en el repositorio.
     *
     * @param value superhéroe a guardar.
     * @return superhéroe guardado.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun saveSuperheroe(value: T):T

    /**
     * Función que actualiza un superhéroe en el repositorio.
     *
     * @param id ID del superhéroe a actualizar.
     * @param value superhéroe con los nuevos valores.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun updateSuperheroe(id: Int, value: T):T?

    /**
     * Función que elimina un superhéroe del repositorio.
     *
     * @param id ID del superhéroe a eliminar.
     * @return superhéroe eliminado, o null si no existe.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun deleteSuperheroe(id: Int):T?
}