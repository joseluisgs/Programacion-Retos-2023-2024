package org.example.repositories.Marvel

import org.lighthousegames.logging.logging

import org.example.exceptions.RepositoryException
import org.example.exceptions.SuperHeroeException
import org.example.models.Marvel
import org.example.repositories.base.CrudRepository
import java.time.LocalDateTime

private const val TAMANIO_REDIMENSION_BASE_MARVEL = 5
private const val TAM_INICIAL_DB = 5

private val log = logging()

class MarvelRepository: CrudRepository<Marvel,Int> {

    private var dbMarvel:Array<Marvel?> = arrayOfNulls<Marvel>(TAM_INICIAL_DB)
    private var clave:Int = 0

    /**
     * Devolvemos todos una copia del repositorio pero sin elementos nulos
     */
    override fun getAll(): Array<Marvel> {
        if (dBEstaVacia()) {
            throw RepositoryException.EmptyRepository()
        } else {
            return dBRepoSinNulos()
        }
    }

    override fun getByName(name: String): Marvel? {
        val sh:Marvel? = null

        for (i in dbMarvel.indices) {
            if ((dbMarvel[i]?.nombre ?: "") == name) return dbMarvel[i]
        }
        return sh
    }

    /** Insertamos el elemento pasado en el primer hueco libre de la base de datos. Si no hay espacio libre, la
     * redimensionamos primero
     * @param value Elemento de la clase Marvel a insertar en la base de datos
     * @throws RepositoryException.FullRepository si en el momento de insertar, no encuentra espacio pese a haber redimensionado
     * @return el elemento guardado, con el nuevo valor de Id obtenido al ser insertado en la base
     */
    override fun save(value: Marvel): Marvel {
        if (!hayEspacioLibreDB()) {
            println("No hay espacio libre en la base de datos. REDIMENSIONANDO")
            aumentarTamanioBase()
        }

        val posicion = getPrimerEspacioLibre()

        // Insertamos el elemento si encontramos una posición válida
        if (posicion >= 0) {
            value.id = ++clave
            dbMarvel[posicion] = value
        } else {
            throw RepositoryException.FullRepository()
        }
        return value

    }


    /**
     * Ordena el repositorio por el Id del superhéroe, y lo devuelve sin elementos nulos
     * @throws RepositoryException.EmptyRepository si la base de datos está vacía
     * @return el repositorio ordenado y sin nulos
     */
    override fun sortById(): Array<Marvel> {

        if (dBEstaVacia()) {
            throw RepositoryException.EmptyRepository()
        } else {
            dBRepoOrdenarPorId()
            return dBRepoSinNulos()
        }
    }

    /**
     * Ordena el repositorio por el nombre del superhéroe, y lo devuelve sin elementos nulos
     * @throws RepositoryException.EmptyRepository si la base de datos está vacía
     * @return el repositorio ordenado y sin nulos
     */
    override fun sortByName(): Array<Marvel> {
        if (dBEstaVacia()) {
            throw RepositoryException.EmptyRepository()
        } else {
            dBRepoOrdenarPorNombre()
            return dBRepoSinNulos()
        }
    }

    /**
     * Eliminamos de la base un superhéroe con un Id dado
     * @param key Id del superhéroe
     * @return objeto Marvel con el superhéroe eliminado, o null en caso de que no se haya encontrado
     */
    override fun delete(key: Int): Marvel? {

        var sh: Marvel? = null
        val posicion:Int = getPosicionSuperHeroe(key)

        if (posicion !=-1) {

            sh = dbMarvel[posicion]
            dbMarvel[posicion] = null
            log.debug { "Correcto, se ha dado de baja al superheroe [ID = ${sh!!.id}] ${sh.nombre}." }

            if ((dbMarvel.size - dBContarNulos()) % TAMANIO_REDIMENSION_BASE_MARVEL == 0) {
                disminuirTamanioBase()
                log.debug { "Se ha REDIMENSIONADO la base de datos" }
            }
        }

        return sh
    }



    /**
     * Modifica en la base los datos de un superhéroe situado en una posición dada, sustituyendo el
     * que había por el nuevo pasado por parámetro
     * @param key Id del superhéroe a modificar
     * @param value Instancia del superhéroe con los nuevos datos
     * @throws IllegalArgumentException si se pasa un objeto superhéroe null
     */
    override fun update(key: Int, value: Marvel): Marvel? {

        var sh: Marvel? = null

        if (existeSuperHeroeMarvel(value.nombre)) {
            log.debug { "Se intentó cambiar el nombre a un superhéroe pero ya existe otro con ese nombre" }
            throw SuperHeroeException.NameNotValid()
        }

        val posicion:Int = getPosicionSuperHeroe(key)

        if (posicion !=-1) {
            dbMarvel[posicion]!!.nombre = value.nombre
            dbMarvel[posicion]!!.alias = value.alias
            dbMarvel[posicion]!!.altura = value.altura
            dbMarvel[posicion]!!.notas = value.notas
            dbMarvel[posicion]!!.updatedAt = LocalDateTime.now()
            sh = dbMarvel[posicion]
            log.debug { "Modificado ${dbMarvel[posicion]?:""}" }
        }

        return sh
    }

    override fun getById(key: Int): Marvel? {
        val sh:Marvel? = null

        for (i in dbMarvel.indices) {
            if ((dbMarvel[i]?.id ?: 0) == key) return dbMarvel[i]
        }
        return sh
    }


    /**
     * Devolvemos un array solo con los elementos no nulos del repositorio
     */
    private fun dBRepoSinNulos(): Array<Marvel> {
        //val numNulos = dBContarNulos()
        var indiceNulos = 0
        //val repoSinNulos = Array<Marvel> (dbMarvel.size - dBContarNulos()){ i -> dbMarvel[i]!!}
        val repoSinNulos = Array<Marvel> (dbMarvel.size - dBContarNulos()){ Marvel() }
        for (i in dbMarvel.indices) {
            if (dbMarvel[i] != null) {
                repoSinNulos[indiceNulos] = dbMarvel[i]!!
                indiceNulos++
            }
        }
        return repoSinNulos
    }

    /**
     * Cuenta los nulos que tiene el repositorio
     */
    private fun dBContarNulos(): Int  {
        var cuenta = 0
        for (i in dbMarvel.indices) {
            if (dbMarvel[i] == null) cuenta ++
        }
        return cuenta
    }

    /**
     * Comprueba si la base de datos de Marvel está vacía
     * @return boolean Verdadero si la base está completamente vacía, falso si hay al menos un hueco ocupado
     */
    private fun dBEstaVacia(): Boolean {
        for (i in dbMarvel.indices) {
            if (dbMarvel[i] != null) return false
        }
        return true
    }


    /**
     * Comprueba si hay espacio libre en la base de datos
     * @return boolean Verdadero si hay al menos un hueco libre
     */
    private fun hayEspacioLibreDB(): Boolean {
        for (i in dbMarvel.indices) {
            if (dbMarvel[i] == null) {
                return true
            }
        }
        return false
    }

    /**
     * Aumenta el tamaño de la base de datos Marvel, sumando al tamaño actual una cantidad establecida al crear la base
     */
    private fun aumentarTamanioBase(){

        val nuevoArray: Array<Marvel?> = Array(dbMarvel.size + TAMANIO_REDIMENSION_BASE_MARVEL) { null }

        for (i in dbMarvel.indices) {
            nuevoArray[i] = dbMarvel[i]
        }

        dbMarvel = nuevoArray

    }
    /**
     * Disminuye el tamaño de la base de datos Marvel, restando al tamaño actual una cantidad  establecida al crear la base
     */
    private fun disminuirTamanioBase() {

        if (dbMarvel.size - TAMANIO_REDIMENSION_BASE_MARVEL < 0) {
            throw RepositoryException.ResizeRepository()
        }

        val nuevoArray: Array<Marvel?> = Array(dbMarvel.size - TAMANIO_REDIMENSION_BASE_MARVEL) { null }
        var nuevoI: Int = 0

        for (i in dbMarvel.indices) {
            if (dbMarvel[i] != null) {
                nuevoArray[nuevoI] = dbMarvel[i]
                nuevoI += 1
            }
        }
        dbMarvel = nuevoArray
    }

    /**
     * Devuelve el primer espacio libre de la base.
     * Si no hay espacio libre, devuelve -1
     * @return Int Primera posición libre. -1 si no hay espacio libre en la base
     */
    private fun getPrimerEspacioLibre(): Int {
        var posicion: Int = -1
        for (i in dbMarvel.indices) {
            if (dbMarvel[i] == null) {
                posicion = i
                return posicion
            }
        }
        return posicion
    }




    /**
     * Ordena el contenido de la base por el campo ID del registro, con el método burbuja de forma ascendente.
     */
    private fun dBRepoOrdenarPorId() {
        var aux: Marvel?
        for (i in 0 until dbMarvel.size) {
            for (j in 0 until dbMarvel.size - 1) {
                val idAnterior: Int = dbMarvel[j]?.id ?: 0
                val idPosterior: Int = dbMarvel[j + 1]?.id ?: 0
                if (idAnterior > idPosterior) {
                    aux = dbMarvel[j]
                    dbMarvel[j] = dbMarvel[j + 1]
                    dbMarvel[j + 1] = aux
                }
            }
        }
    }



    /**
     * Ordena el contenido de la base por el campo nombre del superhéroe, con el método burbuja de forma ascendente.
     */
    private fun dBRepoOrdenarPorNombre() {
        var aux: Marvel?
        for (i in 0 until dbMarvel.size) {
            for (j in 0 until dbMarvel.size - 1) {
                val nombreAnterior: String = dbMarvel[j]?.nombre ?: ""
                val nombrePosterior: String = dbMarvel[j + 1]?.nombre ?: ""
                if (nombreAnterior > nombrePosterior) {
                    aux = dbMarvel[j]
                    dbMarvel[j] = dbMarvel[j + 1]
                    dbMarvel[j + 1] = aux
                }
            }
        }
    }



    /**
     * Comprueba si un superhéroe existe en la base de datos, buscándolo por su nombre
     * @param nombreSH Nombre del superhéroe a localizar
     * @return boolean Verdadero si el superhéroe está en la base, Falso en caso contrario
     */
    private fun existeSuperHeroeMarvel(nombreSH: String): Boolean {

        for (i in dbMarvel.indices) {
            if ((dbMarvel[i]?.nombre ?: "") == nombreSH) {
                return true
            }
        }
        return false
    }

    /**
     * Devuelve la posición de un trabajador en la base, buscándolo por DNI.
     * Si no lo encuentra, devuelve -1
     * @return posición del trabajador. -1 si no existe en la base
     */
    private fun getPosicionSuperHeroe(key: Int): Int {

        for (i in dbMarvel.indices) {
            if ((dbMarvel[i]?.id ?: 0) == key) return i
        }
        return -1
    }
}
