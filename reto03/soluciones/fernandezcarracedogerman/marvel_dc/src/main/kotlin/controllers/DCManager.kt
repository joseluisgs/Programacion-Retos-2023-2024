package org.example.controllers

import org.example.exceptions.SuperHeroeException
import org.example.validators.ValidadorSuperHeroes
import org.example.models.DC
import org.example.repositories.SuperHeroes.DCRepository

private const val TAM_INICIAL_DB = 5
private const val TAMANIO_REDIMENSION_BASE_DC = 5

class DCManager (
    private val repo: DCRepository,
    private val validador: ValidadorSuperHeroes
): DbOperaciones<DC> {

    /**
     * Devolvemos todos una copia del repositorio pero sin elementos nulos
     */
    override fun getAll(): Array<DC> {
        return repo.getAll()
    }

    override fun getById(id: Int): DC {
        return repo.getById(id) ?: throw SuperHeroeException.NotFound(id)
    }

    override fun getByName(name: String): DC {
        return repo.getByName(name) ?: throw SuperHeroeException.NameNotValid()
    }

    override fun delete(id: Int): DC {
        return repo.delete(id) ?: throw SuperHeroeException.NotFound(id)
    }

    override fun sortedById(): Array<DC> {
        return repo.sortById()
    }

    override fun sortedByName(): Array<DC> {
        return repo.sortByName()
    }

    override fun update(id: Int, sH: DC): DC {
        // TODO validador.validar(sH)
        return repo.update(id,sH) ?: throw SuperHeroeException.NotFound(id)
    }

    override fun save(sH: DC): DC {
        // TODO validador.validar(sH)
        return repo.save(sH)
    }
}