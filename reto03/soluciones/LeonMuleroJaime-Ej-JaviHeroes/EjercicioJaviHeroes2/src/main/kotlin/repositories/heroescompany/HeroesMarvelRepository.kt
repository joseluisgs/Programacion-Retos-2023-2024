package org.example.repositories.heroescompany

import org.example.interfaces.PrintFunctions
import org.example.models.Heroe
import org.example.repositories.crud.CrudRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HeroesMarvelRepository : CrudRepository<Heroe, Int>, PrintFunctions {
    private var heroesMarvel: Array<Heroe?> = arrayOfNulls<Heroe>(5)
    private var id: Int = 0

    override fun getAllHeroes(): Array<Heroe> {
        var countNulls = 0
        for (heroe in heroesMarvel) {
            if (heroe == null) countNulls++
        }

        val heroesMarvelSinNulls = Array<Heroe>(heroesMarvel.size - countNulls) {Heroe("", "", 0, 0, "")}
        var index = 0
        for (heroe in heroesMarvel) {
            if (heroe != null) {
                heroesMarvelSinNulls[index] = heroe
                index ++
            }
        }

        return heroesMarvelSinNulls
    }

    override fun getHeroeById(id: Int): Heroe? {
        var heroe: Heroe? = null
        for (i in heroesMarvel.indices) {
            if (heroesMarvel[i]?.id == id) {
                heroe = heroesMarvel[i]
            }
        }
        return heroe
    }

    override fun deleteHeroe(id: Int): Heroe? {
        var heroe: Heroe? = null
        for (i in heroesMarvel.indices) {
            if (heroesMarvel[i]?.id == id) {
                heroe = heroesMarvel[i]
                heroesMarvel[i] = null
            }
        }

        var countNulls = 0
        for (heroeArray in heroesMarvel) {
            if (heroeArray == null) countNulls++
        }

        if (countNulls > 5) {
            val heroesMarvel2 = Array<Heroe?>(heroesMarvel.size - 5) { null }
            var index = 0
            for (heroeArray in heroesMarvel) {
                if (heroeArray != null) {
                    heroesMarvel2[index] = heroeArray
                    index ++
                }
            }
            heroesMarvel = heroesMarvel2
        }

        return heroe
    }

    override fun updateHeroe(id: Int, value: Heroe): Heroe? {
        val heroe = getHeroeById(id)
        if (heroe != null) {
            heroe.nombre = value.nombre
            heroe.alias = value.alias
            heroe.altura = value.altura
            heroe.edad = value.edad
            heroe.notas = value.notas
            heroe.updateAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss a"))
        }
        return heroe
    }

    override fun saveHeroe(value: Heroe): Heroe {
        var countNulls = 0
        for (heroeArray in heroesMarvel) {
            if (heroeArray == null) countNulls++
        }

        if (countNulls == 0) {
            val heroesMarvel2 = Array<Heroe?>(heroesMarvel.size + 5) { null }
            var index = 0
            for (heroeArray in heroesMarvel) {
                if (heroeArray != null) {
                    heroesMarvel2[index] = heroeArray
                    index ++
                }
            }
            heroesMarvel = heroesMarvel2
        }

        var indexFirstNull = 0
        for (i in heroesMarvel.indices) {
            if (heroesMarvel[i] == null) {
                indexFirstNull = i
                break
            }
        }

        id++
        value.id = id
        heroesMarvel[indexFirstNull] = value
        return value
    }

    fun initRepository() {
        heroesMarvel = arrayOfNulls<Heroe>(5)
        id = 0

    }

    fun initExamples() {
        id++
        heroesMarvel[0] = Heroe("Test01Marvel", "Test01Marvel", 100, 30, "Test01Marvel")
        heroesMarvel[0]!!.id = id
        id++
        heroesMarvel[1] = Heroe("Test02Marvel", "Test02Marvel", 120, 40, "Test02Marvel")
        heroesMarvel[1]!!.id = id
    }
}