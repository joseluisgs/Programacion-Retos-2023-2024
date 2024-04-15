package org.example.repositories.personajes

import org.example.models.Personaje
import org.example.services.database.DataBaseManager
import org.lighthousegames.logging.logging
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación concreta del repositorio de personajes.
 *
 * Utiliza la base de datos para almacenar y recuperar los datos de los personajes.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class PersonajesRepositoryImpl : PersonajesRepository {

    init{
        DataBaseManager.use {  }
    }

    private fun ResultSet.toPersonaje(): Personaje {
        return Personaje(
            id = getLong("id"),
            tipo = getString("tipo"),
            nombre = getString("nombre"),
            habilidad = getString("habilidad"),
            ataque = getString("ataque"),
            edad = getInt("edad"),
            arma = getString("arma"),
            createdAt = getString("created_at"),
            updatedAt = getString("updated_at"),
            isDeleted = getBoolean("is_deleted")
        )
    }

    /**
     * Busca y devuelve todas las entidades almacenadas en el repositorio.
     *
     * @return Lista de todas las entidades almacenadas.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findAll(): List<Personaje> {
        logger.debug { "Obteniendo todos los personajes" }
        val result = mutableListOf<Personaje>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM personajes"
            val stmt = db.prepareStatement(sql)
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toPersonaje())
            }
        }
        return result
    }

    /**
     * Busca y devuelve todos los personajes de un tipo específico.
     *
     * @param tipo Tipo de personaje a buscar.
     * @return Lista de personajes del tipo especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findByTipo(tipo: String): List<Personaje> {
        logger.debug { "Obteniendo personajes por tipo: $tipo" }
        val result = mutableListOf<Personaje>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM personajes WHERE tipo = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, tipo)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toPersonaje())
            }
        }
        return result
    }

    /**
     * Busca y devuelve una entidad por su ID.
     *
     * @param id ID de la entidad a buscar.
     * @return La entidad encontrada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun findById(id: Long): Personaje? {
        logger.debug { "Obteniendo personaje por id: $id" }
        var result: Personaje? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM personajes WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setLong(1, id)
            }
            val rs = stmt.executeQuery()
            if (rs.next()) {
                result = rs.toPersonaje()
            }
        }
        return result
    }

    /**
     * Guarda una nueva entidad en el repositorio o actualiza una entidad existente.
     *
     * @param item Entidad a guardar o actualizar.
     * @return La entidad guardada o actualizada.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun save(item: Personaje): Personaje {
        logger.debug { "Guardando personaje: $item" }
        val timeStamp = LocalDateTime.now()
        var result: Personaje = item
        DataBaseManager.use { db ->
            val sql =
                "INSERT INTO personajes (tipo, nombre, habilidad, ataque, edad, arma, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            val stmt = db.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS).apply {
                setString(1, item.tipo)
                setString(2, item.nombre)
                setString(3, item.habilidad)
                setString(4, item.ataque)
                setInt(5, item.edad)
                setString(6, item.arma)
                setString(7, timeStamp.toString())
                setString(8, timeStamp.toString())
                setBoolean(9, false)
            }
            stmt.executeUpdate()
            if (stmt.generatedKeys.next()) {
                result = item.copy(
                    id = stmt.generatedKeys.getLong(1),
                    createdAt = timeStamp.toString(),
                    updatedAt = timeStamp.toString()
                )
            }
        }
        return result
    }

    /**
     * Actualiza una entidad existente por su ID.
     *
     * @param id ID de la entidad a actualizar.
     * @param item Nueva versión de la entidad.
     * @return La entidad actualizada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun update(id: Long, item: Personaje): Personaje? {
        logger.debug { "Actualizando personaje por id: $id" }
        var result: Personaje = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql =
                "UPDATE personajes SET tipo = ?, nombre = ?, habilidad = ?, ataque = ?, edad = ?, arma = ?, updated_at = ? WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, item.tipo)
                setString(2, item.nombre)
                setString(3, item.habilidad)
                setString(4, item.ataque)
                setInt(5, item.edad)
                setString(6, item.arma)
                setString(7, Timestamp.valueOf(LocalDateTime.now()).toString())
                setLong(8, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = item.copy(
                    id = id,
                    updatedAt = LocalDateTime.now().toString()
                )
            }
        }
        return result
    }

    /**
     * Elimina una entidad por su ID.
     *
     * @param id ID de la entidad a eliminar.
     * @param logical Indica si se debe realizar una eliminación lógica (true) o física (false). Por defecto, es false.
     * @return La entidad eliminada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun delete(id: Long, logical: Boolean): Personaje? {
        logger.debug { "Borrando personaje por id: $id" }
        var result: Personaje = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "DELETE FROM personajes WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setLong(1, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = result.copy(isDeleted = true)
            }
        }
        return result
    }
}