package org.example.storage

import org.example.dto.HeroDto
import org.example.mapper.toHero
import org.example.models.Hero
import kotlin.io.path.Path
import kotlin.io.path.readLines

class HeroStorage: FileStorage<Hero> {
    private val file = Path("data", "personajes.csv")
    override fun readFromFile(): List<Hero> {
        return file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map {
                HeroDto(
                    id = it[0].toInt(),
                    nickName = it[1],
                    nombre = it[2],
                    edad = it[3].toInt(),
                    vivo = it[4].toBoolean(),
                    villano = it[5].toBoolean(),
                    habilidad = it[6],
                    pc = it[7].toInt()
                ).toHero()
            }
    }

    override fun writeFromFile(list: List<Hero>) {
        TODO("Not yet implemented")
    }

}