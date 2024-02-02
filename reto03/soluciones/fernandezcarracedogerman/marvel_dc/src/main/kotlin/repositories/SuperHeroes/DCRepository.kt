package org.example.repositories.SuperHeroes

import org.example.models.DC
import org.example.repositories.base.CrudRepository
import java.time.LocalDateTime
import org.lighthousegames.logging.logging

private const val TAM_INICIAL_DB = 5
private const val TAMANIO_REDIMENSION_BASE_DC = 5

private val log = logging()

class DCRepository: CrudRepository <DC,Int> {

    private var dbDC:Array<DC?> = arrayOfNulls<DC>(TAM_INICIAL_DB)
    private var clave:Int = 0

    override fun getAll(): Array<DC> {
        // Creamos un nuevo array con los elementos no nulos del repositorio
        return dbDC.filterNotNull().toTypedArray()
    }

    override fun getByName(name: String): DC? {
        return dbDC.firstOrNull { it?.nombre == name }
    }

    override fun sortById(): Array<DC> {
        return dbDC
            .filterNotNull()
            .sortedBy { it.id }
            .toTypedArray()
    }

    override fun sortByName(): Array<DC> {
        return dbDC
            .filterNotNull()
            .sortedBy { it.nombre }
            .toTypedArray()
    }

    override fun delete(key: Int): DC? {
        // sacamos su indice
        var sh: DC? = null
        this.dbDC.indexOfFirst { it?.id == key }
            .takeIf { it != -1 }
            ?.let {
                // Si existe lo borramos
                sh = dbDC[it]
                dbDC[it] = null
            }.also {
                // Redimensionamos el array si hay mas nulos del tamaño de redimensión
                if (dbDC.count { it == null } > TAMANIO_REDIMENSION_BASE_DC) {
                    dbDC = dbDC.filterNotNull().toTypedArray()
                }
            }
        return sh
    }

    override fun update(key: Int, value: DC): DC? {
        return getById(key)?.apply {
            nombre = value.nombre
            alias = value.alias
            altura = value.altura
            notas = value.notas
            updatedAt = LocalDateTime.now()
            log.debug { "Modificado Id = $id?, Nombre = $nombre" }
        }
    }

    override fun save(value: DC): DC {
        // Si el array está lleno (no tiene nulos)
        if (dbDC.count { it == null } == 0) {
            // Redimensionamos el array
            dbDC = dbDC.copyOf(dbDC.size + 10)
        }
        // Añadimos el elemento en el primer nulo que encontremos
        val index = dbDC.indexOfFirst { it == null }
        value.id = ++clave
        dbDC[index] = value
        return value
    }

    override fun getById(key: Int): DC? {
        return dbDC.firstOrNull { it?.id == key }
    }
}