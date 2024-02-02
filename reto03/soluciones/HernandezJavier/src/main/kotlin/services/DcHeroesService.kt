package org.example.services

import org.example.models.DcHero

interface DcHeroesService {
    fun getAll(): Array<DcHero?>
    fun getById(id: Int): DcHero?
    fun save(persona: DcHero): DcHero
    fun update(id: Int, persona: DcHero): DcHero?
    fun delete(id: Int): DcHero
}