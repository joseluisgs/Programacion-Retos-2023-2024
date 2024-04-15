package org.example.repositories.personaje


import org.example.dto.PersonajeDto
import org.example.exceptions.personaje.PersonajeExceptions
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.service.database.DataBaseManager
import org.lighthousegames.logging.logging
import java.sql.Statement.RETURN_GENERATED_KEYS
import java.time.LocalDate

private val logger = logging()

/**
 * Implementación concreta de [PersonajeRepository] que utiliza una base de datos para almacenar y recuperar personajes.
 */
class PersonajeRepositoryImpl : PersonajeRepository {

    init{
        DataBaseManager.use {  }
    }

    /**
     * Recupera todos los personajes almacenados en la base de datos.
     * @return Una lista de todos los personajes almacenados.
     * @throws PersonajeNotFetchedException Si hay un error al recuperar los personajes.
     */
    override fun findAll(): List<Personaje> {
        logger.debug { "Buscando todos los personajes..." }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personaje"
                val result = db.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        id = result.getInt("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        createdAt = result.getString("createdAt"),
                        updatedAt = result.getString("updatedAt"),
                        isDeleted = result.getBoolean("isDeleted")
                    ).toPersonaje()
                    personajes.add(personaje)
                }
            }
            return personajes
        } catch (e: Exception) {
            logger.error { "Error en la busqueda de todos los personajes" }
            throw PersonajeExceptions.PersonajeNotFetchedException("Error en la busqueda de todos los personajes")
        }
    }
    /**
     * Recupera todos los personajes de un tipo específico almacenados en la base de datos.
     * @param tipo El tipo de personaje a buscar.
     * @return Una lista de personajes del tipo especificado.
     * @throws PersonajeNotFoundException Si no se encuentran personajes del tipo especificado.
     */

    override fun findByTipo(tipo: String): List<Personaje> {
        logger.debug { "Buscando personajes del tipo $tipo" }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personaje WHERE tipo = ?"
                val statement = db.prepareStatement(sql)!!
                statement.setString(1, tipo)
                val result = statement.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        id = result.getInt("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        createdAt = result.getString("createdAt"),
                        updatedAt = result.getString("updatedAt"),
                        isDeleted = result.getBoolean("isDeleted")
                    ).toPersonaje()
                    personajes.add(personaje)
                }
            }
            return personajes
        } catch (e: Exception) {
            logger.error { "Error en la busqueda de todos los personajes del tipo: $tipo" }
            throw PersonajeExceptions.PersonajeNotFoundException("Error en la busqueda de todos los personajes del tipo: $tipo")
        }
    }
    /**
     * Recupera un personaje por su ID.
     * @param id El ID del personaje a recuperar.
     * @return El personaje correspondiente al ID especificado, o nulo si no se encuentra.
     * @throws PersonajeNotFoundException Si no se encuentra ningún personaje con el ID especificado.
     */

    override fun findById(id: Int): Personaje? {
        logger.debug { "Buscando personaje con id: $id" }
        try {
            var personaje: Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personaje WHERE id = ?"
                val statement = db.prepareStatement(sql)!!
                statement.setInt(1, id)
                val result = statement.executeQuery()
                if (result.next()) {
                    personaje = PersonajeDto(
                        id = result.getInt("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        createdAt = result.getString("createdAt"),
                        updatedAt = result.getString("updatedAt"),
                        isDeleted = result.getBoolean("isDeleted")
                    ).toPersonaje()
                }
            }
            return personaje
        } catch (e: Exception) {
            logger.error { "Error en la busqueda del personaje con id: $id" }
            throw PersonajeExceptions.PersonajeNotFoundException("Error en la busqueda del personaje con id: $id")
        }
    }
    /**
     * Guarda un nuevo personaje en la base de datos.
     * @param item El personaje a guardar.
     * @return El personaje guardado.
     * @throws PersonajeNotSavedException Si hay un error al guardar el personaje.
     */

    override fun save(item: Personaje): Personaje {
        logger.debug { "Guardando peronsaje (bbdd) $item" }
        try {
            var personaje: PersonajeDto = item.toPersonajeDto()
            val timeStamp = LocalDate.now()
            DataBaseManager.use { db ->
                val sql = "INSERT INTO personaje (tipo, nombre, habilidad, ataque, edad, arma, createdAt, updatedAt, isDeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                val statement = db.prepareStatement(sql, RETURN_GENERATED_KEYS)!!
                statement.setString(1, personaje.tipo)
                statement.setString(2, personaje.nombre)
                statement.setString(3, personaje.habilidad)
                statement.setInt(4, personaje.ataque)
                statement.setInt(5, personaje.edad)
                statement.setString(6, personaje.arma)
                statement.setString(7, timeStamp.toString())
                statement.setString(8, timeStamp.toString())
                statement.setBoolean(9, false)
                statement.executeUpdate()

                // Recuperar id generado
                val id = statement.generatedKeys.getInt(1)
                personaje = personaje.copy(
                    id = id,
                    createdAt = timeStamp.toString(),
                    updatedAt = timeStamp.toString(),
                    isDeleted = false
                )
            }
            return personaje.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al guardar el personaje $item" }
            throw PersonajeExceptions.PersonajeNotSavedException("Error al guardar el personaje $item, ${e.message}")
        }
    }

    /**
     * Actualiza un personaje existente en la base de datos.
     * @param id El ID del personaje a actualizar.
     * @param item El nuevo estado del personaje.
     * @return El personaje actualizado, o nulo si no se encuentra.
     * @throws PersonajeNotUpdatedException Si hay un error al actualizar el personaje.
     */

    override fun update(id: Int, item: Personaje): Personaje? {
        logger.debug { "Actualizando el personaje con id: $id" }
        try {
            var personaje: PersonajeDto? = this.findById(id)?.toPersonajeDto()
            val idPersonajeSinActualizar = personaje?.id
            if (personaje != null) {
                personaje = item.toPersonajeDto()
                val timeStamp = LocalDate.now()
                DataBaseManager.use { db ->
                    val sql = "UPDATE personaje SET tipo = ?, nombre = ?, habilidad = ?, ataque = ?, edad = ?, arma = ?, updatedAt = ? WHERE id = ?"
                    val statement = db.prepareStatement(sql)!!
                    statement.setString(1, personaje!!.tipo)
                    statement.setString(2, item.nombre)
                    statement.setString(3, item.habilidad)
                    statement.setInt(4, item.ataque)
                    statement.setInt(5, item.edad)
                    statement.setString(6, item.arma)
                    statement.setString(7, timeStamp.toString())
                    statement.setInt(8,id)
                    statement.executeUpdate()

                    personaje = personaje?.copy(
                        id = idPersonajeSinActualizar!!,
                        updatedAt = timeStamp.toString(),
                    )
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al actualizar el personaje con id $id" }
            throw PersonajeExceptions.PersonajeNotUpdatedException("Error al actualizar el personaje con id $id")
        }
    }

    override fun delete(id: Int, logical: Boolean): Personaje? {
        logger.debug { "Borrando personaje con id $id" }
        try {
            var personaje: PersonajeDto? = this.findById(id)?.toPersonajeDto()
            if (personaje != null) {
                DataBaseManager.use { db ->
                    // Borramos logico
                    if (logical) {
                        val sql = "UPDATE personaje SET isDeleted = ? WHERE id = ?"
                        val statement = db.prepareStatement(sql)!!
                        statement.setBoolean(1, true)
                        statement.setInt(2, id)
                        statement.executeUpdate()
                        personaje = personaje?.copy(
                            isDeleted = true
                        )
                    } else {
                        // Borramos fisico
                        val sql = "DELETE FROM personaje WHERE id = ?"
                        val statement = db.prepareStatement(sql)!!
                        statement.setInt(1, id)
                        statement.executeUpdate()
                    }
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al borrar persona con id $id" }
            throw PersonajeExceptions.PersonajeNotDeletedException("Error al borrar persona con id $id")
        }
    }

}