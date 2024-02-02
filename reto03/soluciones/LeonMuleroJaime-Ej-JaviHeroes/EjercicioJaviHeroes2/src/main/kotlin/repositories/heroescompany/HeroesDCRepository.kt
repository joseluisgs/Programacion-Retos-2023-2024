package org.example.repositories.heroescompany

import org.example.interfaces.PrintFunctions
import org.example.models.Heroe
import org.example.repositories.crud.CrudRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HeroesDCRepository : CrudRepository<Heroe, Int>, PrintFunctions {
    private var heroesDC: Array<Heroe?> = arrayOfNulls<Heroe>(5)
    private var id: Int = 0

    override fun getAllHeroes(): Array<Heroe> {
        return heroesDC.filterNotNull().toTypedArray()
    }

    override fun getHeroeById(id: Int): Heroe? {
        return heroesDC.firstOrNull { it?.id == id }
    }

    override fun deleteHeroe(id: Int): Heroe? {
        var heroe: Heroe? = null
        this.heroesDC.indexOfFirst { it?.id == id }
            .takeIf { it != -1 }
            ?.let {
                heroe = this.heroesDC[it]
                this.heroesDC[it] = null
            }.also {
                if (this.heroesDC.count { it == null } > 5) {
                    this.heroesDC = this.heroesDC.filterNotNull().toTypedArray()
                }
            }
        return heroe
    }

    override fun updateHeroe(id: Int, value: Heroe): Heroe? {
        return getHeroeById(id)?.apply {
            nombre = value.nombre
            alias = value.alias
            altura = value.altura
            edad = value.edad
            notas = value.notas
            updateAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss a"))
        }
    }

    override fun saveHeroe(value: Heroe): Heroe {
        if (heroesDC.count { it == null } == 0) {
            heroesDC = heroesDC.copyOf(heroesDC.size + 5)
        }
        val index = heroesDC.indexOfFirst { it == null }
        id++
        value.id = id
        heroesDC[index] = value
        return value
    }

    fun initRepository() {
        heroesDC = arrayOfNulls<Heroe>(5)
        id = 0

    }

    fun initExamples() {
        id++
        heroesDC[0] = Heroe("Test01DC", "Test01DC", 100, 30, "Test01DC")
        heroesDC[0]!!.id = id
        id++
        heroesDC[1] = Heroe("Test02DC", "Test02DC", 120, 40, "Test02DC")
        heroesDC[1]!!.id = id
    }
}