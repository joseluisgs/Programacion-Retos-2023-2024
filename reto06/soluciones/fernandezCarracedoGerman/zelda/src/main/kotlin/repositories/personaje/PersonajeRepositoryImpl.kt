package org.example.repositories.personaje

import org.example.dto.PersonajeDto
import org.example.exceptions.personajes.PersonajesException
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.services.database.DataBaseManager
import org.lighthousegames.logging.logging
import java.sql.Statement.RETURN_GENERATED_KEYS
import java.time.LocalDate

/**
 * Clase que implementa el repositorio de personaje, que desarrolla las operaciones establecidas en los interfaces
 * de repositorio.
 * El repositorios está diseñado para trabajar sobre una base de datos SQLite
 *
 * @see org.example.repositories.crud.CrudRepository
 * @see org.example.repositories.personaje.PersonajeRepository
 */
private val log = logging()

class PersonajeRepositoryImpl: PersonajeRepository {

    /**
     * Función que devuelve una lista con todos los personajes de la base de datos
     * @return List Lista de personajes
     */
    override fun findAll(): List<Personaje> {
        log.debug { "Buscando todos los personajes" }
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM personajes"
                val result = db.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = PersonajeDto(
                        id = result.getLong("id"),
                        nombre = result.getString("nombre"),
                        tipo = result.getString("tipo"),
                        clase = result.getString("clase"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        createdAt = result.getString("created_at"),
                        updatedAt = result.getString("updated_at"),
                        isDeleted = result.getBoolean("is_deleted")
                    ).toPersonaje()
                    personajes.add(personaje)
                }
            }
            return personajes
        } catch (e: Exception) {
            log.error { "Error al buscar todos las personajes" }
            throw PersonajesException.PersonajeNotFetchedException("Error al buscar todos las personajes")
        }
    }

    /**
     * Función que devuelve un personaje con un id pasado por parámetro
     * @param id id del personaje
     * @return Personaje
     */
    override fun findById(id: Long): Personaje? {

        log.debug{ "Recuperando personaje por id $id"}
        try{
            var personaje:Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM Personajes WHERE id = ?"
                val statement = db.prepareStatement(sql)!!
                statement.setLong(1, id)
                val result = statement.executeQuery()
                if (result.next()) {
                    personaje = PersonajeDto(
                        id = result.getLong("id"),
                        nombre = result.getString("nombre"),
                        tipo = result.getString("tipo"),
                        clase = result.getString("clase"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma"),
                        createdAt = result.getString("created_at"),
                        updatedAt = result.getString("updated_at"),
                        isDeleted = result.getBoolean("is_deleted")
                    ).toPersonaje()
                }
            }

            return personaje

        } catch (e:Exception) {
            log.error { "Error al buscar personaje por id $id" }
            throw PersonajesException.PersonajeNotFound("Error al buscar personaje por id $id")
        }
    }

    /**
     * Función que guarda un personaje nuevo en la base de datos
     * @param item objeto personaje a guardar
     * @return objeto con el personaje guardado
     */
    override fun save(item: Personaje): Personaje {
        log.debug { "Guardando personaje $item " }
        try {
            var personaje: PersonajeDto = item.toPersonajeDto()
            val timeStamp = LocalDate.now()
            DataBaseManager.use { db->
                val sql = "INSERT INTO Personajes (nombre,tipo,clase,habilidad,ataque,edad,arma,created_at,updated_at,is_deleted)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                val statement = db.prepareStatement(sql, RETURN_GENERATED_KEYS)!!
                with (statement){
                    setString(1,personaje.nombre)
                    setString(2,personaje.tipo)
                    setString(3,personaje.clase)
                    setString(4,personaje.habilidad)
                    setInt(5,personaje.ataque)
                    setInt(6,personaje.edad)
                    setString(7,personaje.arma)
                    setString(8,timeStamp.toString())
                    setString(9,timeStamp.toString())
                    setBoolean(10,false)
                    executeUpdate()
                }
                val id = statement.generatedKeys.getLong(1)
                personaje = personaje.copy(
                    id=id,
                    createdAt = timeStamp.toString(),
                    updatedAt = timeStamp.toString(),
                    isDeleted = false
                )

            }
            return personaje.toPersonaje()

        } catch (e: Exception) {
            log.error { "Error al guardar personaje $item" }
            throw PersonajesException.PersonajeNotSavedException ("Error al guardar personaje $item")
        }
    }

    /**
     * Función que actualiza un personaje existente en la base de datos
     * @param id id del personaje a modificar
     * @param item objeto personaje con los nuevos datos
     * @return objeto con el personaje modificado
     */
    override fun update(id: Long, item: Personaje): Personaje? {
        log.debug { "Actualizando personaje $item" }
        try {
            var personaje: PersonajeDto? = this.findById(id)?.toPersonajeDto()
            if (personaje != null) {
                personaje = item.toPersonajeDto()
                val timeStamp = LocalDate.now()
                DataBaseManager.use { db ->
                    val sql =
                        //nombre,tipo,clase,habilidad,ataque,edad,arma,created_at,updated_at,is_deleted
                        "UPDATE personajes SET nombre = ?, tipo = ?, clase = ?, habilidad = ?, ataque = ?, edad = ?, " +
                                "arma = ?, updated_at = ? WHERE id = ?"
                    val statement = db.prepareStatement(sql)!!
                    with(statement) {
                        setString(1, personaje?.nombre)
                        setString(2, personaje?.tipo)
                        setString(3, personaje?.clase)
                        setString(4, personaje?.habilidad)
                        setObject(5, personaje?.ataque)
                        setObject(6, personaje?.edad)
                        setString(7, personaje?.arma)
                        setString(8, timeStamp.toString())
                        setLong(9, id)
                        executeUpdate()
                    }
                    personaje = personaje?.copy(
                        updatedAt = timeStamp.toString()
                    )
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            log.error { "Error al actualizar personaje $item" }
            throw PersonajesException.PersonajeNotUpdatedException("Error al actualizar personaje $item")
        }    }

    /**
     * Función elimina de la base de datos un personaje cuyo id se pasa por parámetro
     * @param id id del personaje
     * @param logical Si es true, se hace un borrado lógico, en caso contrario el borrado será físico
     * @return List Lista de personajes
     */
    override fun delete(id: Long, logical: Boolean): Personaje? {
        log.debug { "Borrando personaje con id $id" }
        try {
            var personaje: PersonajeDto? = this.findById(id)?.toPersonajeDto()
            if (personaje != null) {
                DataBaseManager.use { db ->
                    // Borramos logico
                    if (logical) {
                        val sql = "UPDATE personajes SET is_deleted = ? WHERE id = ?"
                        val statement = db.prepareStatement(sql)!!
                        statement.setBoolean(1, true)
                        statement.setLong(2, id)
                        statement.executeUpdate()
                        personaje = personaje?.copy(
                            isDeleted = true
                        )
                    } else {
                        // Borramos fisico
                        val sql = "DELETE FROM personajes WHERE id = ?"
                        val statement = db.prepareStatement(sql)!!
                        statement.setLong(1, id)
                        statement.executeUpdate()
                    }
                }
            }
            return personaje?.toPersonaje()
        } catch (e: Exception) {
            log.error { "Error al borrar personaje con id $id" }
            throw PersonajesException.PersonajeNotDeletedException("Error al borrar personaje con id $id")
        }
    }
    /**
     * Función que devuelve una lista con todos los personajes del tipo dado por parámetro
     * @param type Tipo a buscar
     * @return List Lista de personajes
     */
    override fun findByType(type: String): List<Personaje> {
        log.debug { "Buscando personajes por tipo $type" }
        try {
            val personajes = mutableListOf<Personaje>()

            DataBaseManager.use { db ->
                val sql = "SELECT * FROM Personajes WHERE tipo = ?"
                val statement = db.prepareStatement(sql)!!
                statement.setString(1,type)
                val result = statement.executeQuery()
                while (result.next()) {
                    with(result) {
                        val personaje = PersonajeDto(
                            id = getLong("id"),
                            nombre = getString("nombre"),
                            tipo = getString("tipo"),
                            clase = getString("clase"),
                            habilidad = getString("habilidad"),
                            ataque = getInt("ataque"),
                            edad = getInt("edad"),
                            arma = getString("arma"),
                            createdAt = getString("created_at"),
                            updatedAt = getString("updated_at"),
                            isDeleted = getBoolean("is_deleted")
                            ).toPersonaje()
                        personajes.add(personaje)
                    }
                }
            }

            return personajes

        } catch (e:Exception) {
            log.error { "Error buscando personajes por tipo $type" }
            throw PersonajesException.PersonajeNotFetchedException("Error buscando personajes por tipo $type")
        }
    }
}