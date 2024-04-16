package repositories.personajes

import exceptions.personaje.PersonajeException
import models.Personaje
import services.database.DataBaseManager


class PersonajeRepositoryImpl : PersonajeRepository {

    override fun findAll(): List<Personaje> {
        println("Buscando todos los personajes")
        try {
            val personajes = mutableListOf<Personaje>()
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM PERSONAJE"
                val result = db.connection?.prepareStatement(sql)!!.executeQuery()
                while (result.next()) {
                    val personaje = Personaje(
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma")
                    )
                    personajes.add(personaje)
                }
            }
            return personajes
        }catch (e: Exception){
            println("Error al buscar todos las personajes")
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar todos los personajes")
        }
    }

    override fun findByNombre(nombre: String): Personaje? {
        println("Buscando personajes con nombre $nombre")
        try {
            var personaje : Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM PERSONAJE WHERE NOMBRE = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1,nombre)
                val result = statement.executeQuery()
                while (result.next()) {
                     personaje = Personaje(
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma")
                    )
                }
            }
            return personaje
        }catch (e: Exception){
            println("Error al buscar todas la persona con nombre $nombre")
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar el personaje con ese nombre ")
        }
    }

    override fun findByHabilidad(habilidad: String): Personaje? {
        println("Buscando personaje con la habilidad $habilidad")
        try {
            val personaje : Personaje? = null
            DataBaseManager.use { db ->
                val sql = "SELECT * FROM PERSONAJE WHERE HABILIDAD = ?"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1,habilidad)
                val result = statement.executeQuery()
                while (result.next()) {
                    val personaje = Personaje(
                        nombre = result.getString("nombre"),
                        habilidad = result.getString("habilidad"),
                        ataque = result.getInt("ataque"),
                        edad = result.getInt("edad"),
                        arma = result.getString("arma")
                    )
                }
            }
            return personaje
        }catch (e: Exception){
            println("Error al buscar todoss la personaje con nombre $habilidad")
            throw PersonajeException.PersonajeNotFetchedException("Error al buscar el personaje con esa habilidad ")
        }
    }

    override fun save(item: Personaje): Personaje {
        println("Guardando personaje $item")
        try {
            var personaje = item
            DataBaseManager.use { db->
                val sql = "INSERT INTO PERSONAJE(NOMBRE,HABILIDAD,ATAQUE,EDAD,ARMA) VALUES (?,?,?,?,?)"
                val statement = db.connection?.prepareStatement(sql)!!
                statement.setString(1, personaje.nombre)
                statement.setString(2, personaje.habilidad)
                statement.setInt(3,personaje.ataque)
                statement.setObject(4, personaje.edad)
                statement.setString(5, personaje.arma)
            }
            return personaje
        }catch (e: Exception) {
            println("Error al guardar el personaje $item")
            throw PersonajeException.PersonajeNotSavedException("Error al guardar persona $item")
        }
    }

    override fun update(nombre: String, item: Personaje): Personaje? {
        try {
            println("Actualizando el personaje $item")
            var personaje = this.findByNombre(nombre)
            if (personaje != null) {
                DataBaseManager.use { db ->
                    val sql = "UPDATE PERSONAJE SET NOMBRE=?, HABILIDAD=?, ATAQUE=?, EDAD=?, ARMA=? WHERE NOMBRE=?"
                    val statement = db.connection?.prepareStatement(sql)!!
                    statement.setString(1, item.nombre)
                    statement.setString(2, item.habilidad)
                    statement.setInt(3, item.ataque)
                    statement.setObject(4, item.edad)
                    statement.setString(5, item.arma)
                    statement.setString(6, nombre)
                }
            }
            return personaje
        } catch (e: Exception) {
            println("Error al actualizar el personaje $nombre con los datos $item")
            throw PersonajeException.PersonajeNotUpdatedException("Error al actualizar el personaje $nombre con los datos $item")
        }
    }


    override fun delete(name: String, logical: Boolean): Personaje? {
        var personaje=this.findByNombre(name)
        DataBaseManager.use {
            if (logical){
                val sql ="UPDATE personajes SET is_deleted = ? WHERE nombre = ?"
                val statement=it.connection?.prepareStatement(sql)!!
                statement.setBoolean(1,true)
                statement.setString(2,name)
            }else{
                val sql ="DELETE FROM personajes WHERE nombre = ?"
                val statement=it.connection?.prepareStatement(sql)!!
                statement.setString(1,name)
                statement.executeUpdate()
            }
        }
        return personaje
    }
}