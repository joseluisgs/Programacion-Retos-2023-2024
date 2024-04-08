package org.example.repositories.personajes

import org.example.dto.PersonajeDto
import org.example.exceptions.personajes.PersonajeExceptions
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.services.database.DataBaseManager
import org.lighthousegames.logging.logging
import java.time.LocalDate

private val logger = logging()

class PersonajesRepositoryImpl: PersonajesRepository {

    init {
        DataBaseManager.use {  }
    }

    override fun findAll(): List<Personaje> {
        logger.debug { "Buscando todos los personajes..." }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes"
                val result = db.connection?.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        created_at = result.getString("created_at"),
                        updated_at = result.getString("updated_at"),
                        is_deleted = result.getBoolean("is_deleted")
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

    override fun findByTipo(tipo: String): List<Personaje> {
        logger.debug { "Buscando personajes del tipo $tipo" }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes WHERE tipo = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, tipo)
                val result = statement.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        created_at = result.getString("created_at"),
                        updated_at = result.getString("updated_at"),
                        is_deleted = result.getBoolean("is_deleted")
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

    override fun findByName(name: String): Personaje? {
        logger.debug { "Buscando personaje con nombre: $name" }
        try {
            var personaje: Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes WHERE nombre = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, name)
                val result = statement.executeQuery()
                if (result.next()) {
                    personaje = PersonajeDto(
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        created_at = result.getString("created_at"),
                        updated_at = result.getString("updated_at"),
                        is_deleted = result.getBoolean("is_deleted")
                    ).toPersonaje()
                }
            }
            return personaje
        } catch (e: Exception) {
            logger.error { "Error en la busqueda del personajes con nombre: $name" }
            throw PersonajeExceptions.PersonajeNotFoundException("Error en la busqueda del personajes con nombre: $name")
        }    }

    override fun save(item: Personaje): Personaje {
        logger.debug { "Guardando peronsaje (bbdd) $item" }
        try {
            val personaje: PersonajeDto = item.toPersonajeDto()
            val timeStamp = LocalDate.now()
            DataBaseManager.use { db ->
                val sql = "INSERT INTO personajes (tipo, nombre, habilidad, ataque, edad, arma, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                val statement = db.connection?.prepareStatement(sql)!!
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
            }
            return personaje.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al guardar el personaje $item" }
            throw PersonajeExceptions.PersonajeNotSavedException("Error al guardar el personaje $item, ${e.message}")
        }
    }

    override fun update(name: String, item: Personaje): Personaje? {
        logger.debug { "Actualizando el personaje llamado: $name" }
        try {
            var personaje: PersonajeDto? = this.findByName(name)?.toPersonajeDto()
            if (personaje != null) {
                personaje = item.toPersonajeDto()
                val timeStamp = LocalDate.now()
                DataBaseManager.use { db ->
                    val sql = "UPDATE personajes SET tipo = ?, habilidad = ?, ataque = ?, edad = ?, arma = ?, updated_at = ? WHERE nombre = ?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setString(1, personaje.tipo)
                    statement.setString(2, item.habilidad)
                    statement.setInt(3, item.ataque)
                    statement.setInt(4, item.edad)
                    statement.setString(5, item.arma)
                    statement.setString(6, timeStamp.toString())
                    statement.setString(7, name)
                    statement.executeUpdate()
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al actualizar el personaje llamado $name" }
            throw PersonajeExceptions.PersonajeNotUpdatedException("Error al actualizar el personaje llamado $name")
        }
    }

    override fun deleteFisico(name: String): Personaje? {
        logger.debug { "Realizando borrado fisico del personaje llamado $name" }
        try {
            val personaje: PersonajeDto? = this.findByName(name)?.toPersonajeDto()
            if (personaje != null) {
                DataBaseManager.use { db ->
                    val sql = "DELETE FROM personajes WHERE nombre = ?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setString(1, name)
                    statement.executeUpdate()
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al realizar el borrado físico del personaje llamado $name" }
            throw PersonajeExceptions.PersonajeNotDeletedException("Error al realizar el borrado físico del personaje llamado $name")
        }
    }

    override fun deleteLogico(name: String): Personaje? {
        logger.debug { "Realizando borrado lógico del personaje llamado $name" }
        try {
            var personaje: PersonajeDto? = this.findByName(name)?.toPersonajeDto()
            if (personaje != null) {
                DataBaseManager.use { db ->
                    val sql = "UPDATE personajes SET is_deleted = ? WHERE nombre = ?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setBoolean(1, true)
                    statement.setString(2, name)
                    statement.executeUpdate()
                    personaje = personaje?.copy(
                        is_deleted = true
                    )
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            logger.error { "Error al realizar el borrado lógico del personaje llamado $name" }
            throw PersonajeExceptions.PersonajeNotDeletedException("Error al realizar el borrado lógico del personaje llamado $name")
        }
    }
}