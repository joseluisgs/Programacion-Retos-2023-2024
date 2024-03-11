package storage

import models.Personaje
import java.io.File

class PersonajeStorage: Storage<Personaje>{
    override fun load(file: File): List<Personaje> {
        if (!file.exists()) throw IllegalArgumentException()
        return file.readLines().drop(1).map { it.split(",")}.map {
            Personaje(
                it[0].toInt(),
                it[1],
                it[2],
                it[3].toInt(),
                it[4].toBoolean(),
                it[5].toBoolean(),
                it[6],
                it[7].toInt()
            )
        }
    }

    override fun save(lista: List<Personaje>, file: File) {
        if (!file.exists()) file.createNewFile()
        file.appendText("ID,NickName,Nombre,Edad,Vivo,Villano,Habilidad,PC")
        lista.forEach {
            file.appendText("${it.id},${it.nickName},${it.nombre}")
        }
    }
}