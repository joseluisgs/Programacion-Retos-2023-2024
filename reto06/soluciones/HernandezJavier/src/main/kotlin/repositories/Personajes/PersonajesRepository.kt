package org.example.repositories.Personajes

import org.example.dto.PersonajeDto
import org.example.exceptions.personajes.PersonajeException
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.repositories.crud.CrudRepository
import org.example.services.database.DatabaseManager
import org.lighthousegames.logging.logging

private val logger = logging()
class PersonajesRepository: CrudRepository<Personaje, String> {
    override fun findAll(): List<Personaje> {
        logger.debug { "Buscando todos los personajes" }
        try {
            val personajes = mutableListOf<Personaje>()
            DatabaseManager.use { db ->
                val sql = "SELECT * FROM personajes"
                val result = db.connection?.prepareStatement(sql)!!.executeQuery()
                while (result.next()){
                    val personaje = PersonajeDto(
                        nombre = result.getString("nombre"),
                        tipo = result.getString("tipo"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        isDeleted = result.getBoolean("is_Deleted")
                    ).toPersonaje()
                    personajes.add(personaje)
                }
            }
            return personajes
        }catch (e: Exception){
            logger.error { "Error al buscar todas los personajes" }
            throw PersonajeException.PersonajesNotFetchedException("Error al buscar todas las personas")
        }
    }

    override fun delete(nombre: String, logical: Boolean): Personaje? {
        logger.debug { "Borrando personaje con nombre: $nombre" }
        try {
            var personaje: PersonajeDto? = this.getByName(nombre)?.toPersonajeDto()
            if(personaje != null){
                DatabaseManager.use { db ->
                    if(logical){
                        val sql = "UPDATE personajes SET is_deleted = ? WHERE nombre = ?"
                        val statement = db.connection?.prepareStatement(sql)!!
                        statement.setBoolean(1, true)
                        statement.setString(2, nombre)
                        statement.executeUpdate()
                        personaje = personaje?.copy(
                            isDeleted = true
                        )
                    }else{
                        val sql = "DELETE * FROM personajes WHERE id = ?"
                        val statement = db.connection?.prepareStatement(sql)!!
                        statement.setString(1, nombre)
                        statement.executeUpdate()
                    }
                }
            }
            return personaje?.toPersonaje()
        }catch (e: Exception){
            logger.error { "Error al borrar persona con nombre $nombre" }
            throw PersonajeException.PersonajeNotDeletedException("Error al borrar persona con nombre $nombre")
        }
    }

    override fun update(name: String, item: Personaje): Personaje? {
        logger.debug { "Actualizando personaje $item" }
        try {
            var personaje: PersonajeDto? = this.getByName(name)?.toPersonajeDto()
            if(personaje != null){
                personaje = item.toPersonajeDto()
                DatabaseManager.use { db ->
                    val sql = "UPDATE personajes SET tipo = ?, habilidad = ?, ataque = ?, edad = ?, arma = ? WHERE nombre = ?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setString(1, personaje.nombre)
                    statement.setString(2, personaje.tipo)
                    statement.setString(3, personaje.habilidad)
                    statement.setInt(4,personaje.ataque)
                    statement.setInt(5, personaje.edad)
                    statement.setString(6, personaje.arma)
                    statement.setBoolean(7, false)
                    statement.executeUpdate()
                }
            }
            return personaje?.toPersonaje()
        }catch (e:Exception){
            logger.error { "Error al actualziar personas $item" }
            throw PersonajeException.PersonajeNotUpdatedException("Error al actualizar el personaje $item")
        }
    }

    override fun save(item: Personaje): Personaje {
        logger.debug { "Guardando personaje $item" }
        try {
            var personaje: PersonajeDto = item.toPersonajeDto()
            DatabaseManager.use { db ->
                val sql = "INSERT INTO personajes (nombre, tipo, habilidad, ataque, edad, arma, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, personaje.nombre)
                statement.setString(2, personaje.tipo)
                statement.setString(3, personaje.habilidad)
                statement.setInt(4,personaje.ataque)
                statement.setInt(5, personaje.edad)
                statement.setString(6, personaje.arma)
                statement.setBoolean(7, false)
                statement.executeUpdate()
            }
            return personaje.toPersonaje()
        }catch (e:Exception){
            logger.error { "Error al guardar personaje $item" }
            throw PersonajeException.PersonajeNotSavedException("Error al guardar personaje $item")
        }
    }

    override fun getByName(name: String): Personaje? {
        logger.debug { "Buscando personaje por nombre: $name" }
        try {
            var personaje: Personaje? = null
            DatabaseManager.use { db ->
                val sql = "SELECT * FROM personajes WHERE nombre = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, name)
                val result = statement.executeQuery()
                if(result.next()){
                    personaje = PersonajeDto(
                        nombre = result.getString("nombre"),
                        tipo = result.getString("tipo"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        isDeleted = result.getBoolean("is_Deleted")
                    ).toPersonaje()
                }
            }
            return personaje
        }catch (e: Exception){
            logger.error { "Error al buscar a la persona por nombre $name" }
            throw PersonajeException.PersonajeNotFoundException("Error al buscar persona por nombre $name")
        }
    }
}