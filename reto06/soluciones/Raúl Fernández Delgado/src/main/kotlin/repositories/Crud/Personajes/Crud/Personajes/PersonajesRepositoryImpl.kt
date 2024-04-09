class PersonajesRepositoryImpl:PersonajesRepository {
    override fun findAll(): List<Personaje> {
        val personajes= mutableListOf<Personaje>()
        DataBaseManager.use {
            val sql="SELECT * FROM personajes"
            val resultado= it.connection?.prepareStatement(sql)!!.executeQuery()
            while (resultado.next()){
                val personaje=PersonajeDto(
                    nombre = resultado.getString("nombre"),
                    tipo = resultado.getString("tipo"),
                    habilidad = resultado.getString("habilidad"),
                    ataque = resultado.getInt("ataque"),
                    edad = resultado.getInt("edad"),
                    arma= resultado.getString("arma"),
                    isDeleted = resultado.getBoolean("is_deleted")
                ).toPersonaje()
                personajes.add(personaje)
            }
        }
        return personajes
    }

    override fun findByName(name: String): Personaje? {
        var personaje:Personaje?=null
        DataBaseManager.use {
            val sql = "SELECT * FROM personajes WHERE nombre = ?"
            val statement=it.connection?.prepareStatement(sql)!!
            statement.setString(1,name)
            val resultado=statement.executeQuery()
            if (resultado.next()){
                personaje=PersonajeDto(
                    nombre = resultado.getString("nombre"),
                    tipo = resultado.getString("tipo"),
                    habilidad = resultado.getString("habilidad"),
                    ataque=resultado.getInt("ataque"),
                    edad=resultado.getInt("edad"),
                    arma=resultado.getString("arma"),
                    isDeleted=resultado.getBoolean("is_deleted")
                ).toPersonaje()
            }
        }
        return personaje
    }

    override fun save(item: Personaje): Personaje {
        val personaje=item.toPersonajeDto()
        DataBaseManager.use {
            val sql =
                "INSERT INTO personajes(nombre, tipo, habilidad, ataque, edad, arma, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)"
            val statement=it.connection?.prepareStatement(sql)!!
            statement.setString(1,personaje.nombre)
            statement.setString(2,personaje.tipo)
            statement.setString(3,personaje.habilidad)
            statement.setInt(4,personaje.ataque)
            statement.setInt(5,personaje.edad)
            statement.setString(6,personaje.arma)
            statement.setBoolean(7,false)
            statement.executeUpdate()
        }
        return personaje.toPersonaje()
    }

    override fun update(name: String, item: Personaje): Personaje? {
        var personaje=this.findByName(name)?.toPersonajeDto()
        if (personaje != null){
            personaje=item.toPersonajeDto()
            DataBaseManager.use {
                val sql=
                    "UPDATE personaje SET tipo = ?, habilidad = ?, ataque = ?, edad = ?, arma = ? WHERE nombre = ?"
                val statement = it.connection?.prepareStatement(sql)!!
                statement.setString(1,personaje.tipo)
                statement.setString(2, personaje.habilidad)
                statement.setInt(3,personaje.ataque)
                statement.setInt(4,personaje.edad)
                statement.setString(5,personaje.arma)
                statement.setString(6,personaje.nombre)
                statement.executeUpdate()
            }
        }
        return personaje?.toPersonaje()
    }

    override fun delete(name: String, logical: Boolean): Personaje? {
        var personaje=this.findByName(name)?.toPersonajeDto()
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
        return personaje?.toPersonaje()
    }
}