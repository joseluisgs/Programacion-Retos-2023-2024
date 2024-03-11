package org.example.mapper

import org.example.dto.HeroDto
import org.example.models.Hero

fun HeroDto.toHero(): Hero{
    return Hero(this.id, this.nickName, this.nombre, this.edad, this.vivo, this.villano, this.habilidad, this.pc)
}

fun Hero.toHeroDtop(): HeroDto {
    return HeroDto(this.id, this.nickName, this.nombre, this.edad, this.vivo, this.villano, this.habilidad, this.pc)
}