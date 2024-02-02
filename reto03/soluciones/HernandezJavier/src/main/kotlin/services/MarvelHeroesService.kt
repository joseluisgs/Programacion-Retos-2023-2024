package org.example.services

import org.example.models.DcHero
import org.example.models.MarvelHero

interface MarvelHeroesService {
    fun getAll(): Array<MarvelHero?>
    fun getById(id: Int): MarvelHero?
    fun save(persona: MarvelHero): MarvelHero
    fun update(id: Int, persona: MarvelHero): MarvelHero?
    fun delete(id: Int): MarvelHero
}