package org.example.repositories.personaje

import org.example.database.DataBaseManager
import org.example.exceptions.personaje.PersonajeException
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import java.sql.Statement
import java.time.LocalDate

private val log = logging()

class PersonajeRepositoryImpl: PersonajeRepository {
    override fun findAll(): List<Personaje> {
        log.debug { "Buscando todos los personajes" }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes"
                val result = db.connection?.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = Personaje(
                        id = result.getLong("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getString("ataque"),
                        edad = result.getString("edad"),
                        arma = result.getString("arma")
                    )
                    personajes.add(personaje)
                }
            }
            return personajes
        } catch (e: Exception) {
            log.error { "Error al buscar todos los personajes" }
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar todos los personajes")
        }
    }

    override fun findByTipo(tipo: String): List<Personaje> {
        log.debug { "Buscando personas por tipo $tipo" }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes WHERE tipo = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, tipo)
                val result = statement.executeQuery()
                while (result.next()) {
                    val personaje = Personaje(
                        id = result.getLong("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getString("ataque"),
                        edad = result.getString("edad"),
                        arma = result.getString("arma")
                    )
                    personajes.add(personaje)
                }
            }
            return personajes
        } catch (e: Exception) {
            log.error { "Error al buscar personas por tipo $tipo" }
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar personas por tipo $tipo")
        }
    }

    override fun findById(id: Long): Personaje? {
        log.debug { "Buscando personaje por id $id" }
        try {
            var personaje: Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes WHERE id = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setLong(1, id)
                val result = statement.executeQuery()
                if (result.next()) {
                    personaje = Personaje(
                        id = result.getLong ("id"),
                        tipo = result.getString("tipo"),
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getString("ataque"),
                        edad = result.getString("edad"),
                        arma = result.getString("arma")
                    )
                }
            }
            return personaje
        } catch (e: Exception) {
            log.error { "Error al buscar personaje por id $id" }
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar personaje por id $id")
        }
    }

    override fun save(item: Personaje): Personaje {
        log.debug { "Guardando persona $item" }
        try {
            var personaje: Personaje = item
            val timeStamp = LocalDate.now()
            DataBaseManager.use { db ->
                val sql =
                    "INSERT INTO personajes (tipo, nombre, habilidad, ataque, edad, arma) VALUES (?, ?, ?, ?, ?)"
                val statement = db.connection?.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)!!
                statement.setString(1, personaje.tipo)
                statement.setString(2, personaje.nombre)
                statement.setString(3, personaje.habilidad)
                statement.setString(4, personaje.ataque)
                statement.setString(5, personaje.edad)
                statement.setString(6, personaje.arma)
                statement.executeUpdate()
                // Si queremos recuperar el ID generado
                val id = statement.generatedKeys.getLong(1)
                personaje = personaje.copy(
                    id = id,

                )
            }
            return personaje
        } catch (e: Exception) {
            log.error { "Error al guardar personaje $item" }
            throw PersonajeException.PersonajeNotSavedException("Error al guardar personaje $item")
        }
    }

    override fun update(id: Long, item: Personaje): Personaje? {
        log.debug { "Actualizando personaje $item" }
        try {
            var personaje: Personaje? = this.findById(id)
            if (personaje != null) {
                personaje = item
                val timeStamp = LocalDate.now()
                DataBaseManager.use { db ->
                    val sql =
                        "UPDATE personajes SET tipo = ?, nombre = ?, habilidad = ?, ataque = ?, edad = ?, arma = ? WHERE id = ?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setString(1, personaje?.tipo)
                    statement.setString(2, personaje?.nombre)
                    statement.setString(3, personaje?.habilidad)
                    statement.setString(4, personaje?.ataque)
                    statement.setString(5, personaje?.edad)
                    statement.setString(6, personaje?.arma)
                    statement.setLong(7, id)
                    statement.executeUpdate()
                }
            }
            return personaje
        } catch (e: Exception) {
            log.error { "Error al actualizar personaje $item" }
            throw PersonajeException.PersonajeNotUpdatedException("Error al actualizar personaje $item")
        }
    }

    override fun delete(id: Long, logical: Boolean): Personaje? {
        log.debug { "Borrando personaje con id $id" }
        try {
            var personaje: Personaje? = this.findById(id)
            if (personaje != null) {
                DataBaseManager.use { db ->
                    // Borramos logico
                    if (logical) {
                        val sql = "UPDATE personajes SET is_deleted = ? WHERE id = ?"
                        val statement = db.connection?.prepareStatement(sql)!!
                        statement.setBoolean(1, true)
                        statement.setLong(2, id)
                        statement.executeUpdate()
                        personaje = personaje?.copy(
                            isDeleted = true
                        )
                    } else {
                        // Borramos fisico
                        val sql = "DELETE FROM personajes WHERE id = ?"
                        val statement = db.connection?.prepareStatement(sql)!!
                        statement.setLong(1, id)
                        statement.executeUpdate()
                    }
                }
            }
            return personaje
        } catch (e: Exception) {
            log.error { "Error al borrar personaje con id $id" }
            throw PersonajeException.PersonajeNotDeletedException("Error al borrar personaje con id $id")
        }
    }


}