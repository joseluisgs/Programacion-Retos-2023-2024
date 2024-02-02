package org.example.controllers

import org.example.exceptions.SuperHeroeException
import org.example.validators.ValidadorSuperHeroes
import org.example.models.Marvel
import org.example.repositories.Marvel.MarvelRepository

private const val TAM_INICIAL_DB = 5
private const val TAMANIO_REDIMENSION_BASE_DC = 5

class MarvelManager(
    private val repo: MarvelRepository,
    private val validador: ValidadorSuperHeroes
): DbOperaciones<Marvel> {

    /**
     * Devolvemos todos una copia del repositorio pero sin elementos nulos
     */
    override fun getAll(): Array<Marvel> {
        return repo.getAll()
    }

    override fun getById(id: Int): Marvel {
        return repo.getById(id) ?: throw SuperHeroeException.NotFound(id)
    }

    override fun getByName(name: String): Marvel {
        return repo.getByName(name)?: throw SuperHeroeException.NameNotValid()
    }

    override fun delete(id: Int): Marvel {
        return repo.delete(id) ?: throw SuperHeroeException.NotFound(id)
    }

    override fun sortedById(): Array<Marvel> {
        return repo.sortById()
    }

    override fun sortedByName(): Array<Marvel> {
        return repo.sortByName()
    }

    override fun update(id: Int, sH: Marvel): Marvel {
        // TODO validador.validar(sH)
        return repo.update(id,sH)?: throw SuperHeroeException.NotFound(id)
    }

    override fun save(sH: Marvel): Marvel {
        // TODO validador.validar(sH)
        return repo.save(sH)
    }
}