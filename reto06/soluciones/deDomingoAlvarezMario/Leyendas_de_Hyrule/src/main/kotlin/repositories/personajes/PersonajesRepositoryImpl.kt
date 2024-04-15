package org.example.repositories.personajes

import PersonajeDto
import org.example.exceptions.PersonajeEsception
import org.example.services.database.DataBaseManager
import org.lighthousegames.logging.logging

private val logger = logging()

class PersonajesRepositoryImpl : PersonajesRepository {

    override fun findAll(): List<PersonajeDto> {
        logger.debug { "Buscando todas las personas" }
        try {
            val personajes = mutableListOf<PersonajeDto>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personas"
                val result = db.connection?.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        id = result.getLong("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                    )
                    personajes.add(personaje)

                }
            }
            return personajes
        } catch (e: Exception) {
            logger.error { "Error al buscar todas las personas" }
            throw PersonajeEsception.PersonajeNotFetchedException("Error al buscar todas las personas")
        }
    }

    override fun findByTipo(tipo: String): List<PersonajeDto> {
        logger.debug { "Buscando personas por tipo $tipo" }
        try {
            val personas = mutableListOf<PersonajeDto>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personas WHERE tipo = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, tipo)
                val result = statement.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        id = result.getLong("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                    )
                    personas.add(personaje)
                }
            }
            return personas
        } catch (e: Exception) {
            logger.error { "Error al buscar personas por tipo $tipo" }
            throw PersonajeEsception.PersonajeNotFetchedException("Error al buscar personas por tipo $tipo")
        }
    }

    override fun findById(id: Long): PersonajeDto? {
        logger.debug { "Buscando persona por id $id" }
        try {
            var personaje: PersonajeDto? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personas WHERE id = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setLong(1, id)
                val result = statement.executeQuery()
                if (result.next()) {
                    personaje= PersonajeDto(
                        id = result.getLong("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                    )
                }
            }
            return personaje
        } catch (e: Exception) {
            logger.error { "Error al buscar persona por id $id" }
            throw PersonajeEsception.PersonajeNotFetchedException("Error al buscar persona por id $id")
        }
    }

    override fun save(item: PersonajeDto): PersonajeDto {
        logger.debug { "Guardando persona $item" }
        try {
            DataBaseManager.use { db ->
                val sql =
                    "INSERT INTO personas (id, tipo, nombre, habilidad, ataque, edad, arma) VALUES (?, ?, ?, ?, ?, ?, ?)"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setLong(1, item.id)
                statement.setString(2, item.tipo)
                statement.setString(3, item.nombre)
                statement.setString(4, item.habilidad)
                statement.setInt(5, item.ataque)
                statement.setInt(6, item.edad)
                statement.setString(7, item.arma)
                statement.executeUpdate()
            }
            return item // Retornar el objeto guardado
        } catch (e: Exception) {
            logger.error { "Error al guardar persona $item: ${e.message}" }
            throw PersonajeEsception.PersonaNotSavedException("Error al guardar persona $item")
        }
    }
    override fun update(id: Long, item: PersonajeDto): PersonajeDto {
        logger.debug { "Actualizando persona con id $id: $item" }
        try {
            DataBaseManager.use { db ->
                val sql =
                    "UPDATE personas SET tipo = ?, nombre = ?, habilidad = ?, ataque = ?, edad = ?, arma = ? WHERE id = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, item.tipo)
                statement.setString(2, item.nombre)
                statement.setString(3, item.habilidad)
                statement.setInt(4, item.ataque)
                statement.setInt(5, item.edad)
                statement.setString(6, item.arma)
                statement.setLong(7, id)
                statement.executeUpdate()
            }
            return item // Retornar el objeto actualizado
        } catch (e: Exception) {
            logger.error { "Error al actualizar persona con id $id: ${e.message}" }
            throw PersonajeEsception.PersonaNotUpdatedException("Error al actualizar persona con id $id")
        }
    }

    override fun delete(id: Long, logical: Boolean): PersonajeDto? {
        logger.debug { "Borrando persona con id $id" }
        try {
            val personaje = findById(id) ?: throw PersonajeEsception.PersonajeNotFoundException("No se encontró ninguna persona con el id $id")
            DataBaseManager.use { db ->
                val sql = if (logical) {
                    "UPDATE personas SET is_deleted = ? WHERE id = ?"
                } else {
                    "DELETE FROM personas WHERE id = ?"
                }
                val statement = db.connection?.prepareStatement(sql)!!
                if (logical) {
                    statement.setBoolean(1, true)
                    statement.setLong(2, id)
                } else {
                    statement.setLong(1, id)
                }
                statement.executeUpdate()
            }
            return personaje
        } catch (e: Exception) {
            logger.error { "Error al borrar persona con id $id: ${e.message}" }
            throw PersonajeEsception.PersonaNotDeletedException("Error al borrar persona con id $id")
        }
    }

}
