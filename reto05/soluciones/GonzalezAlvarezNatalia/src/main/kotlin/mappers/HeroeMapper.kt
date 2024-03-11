package org.example.mappers

import org.example.dto.HeroeDto
import org.example.models.Heroe

/**
 * Función de extensión que convierte un objeto de la clase Heroe en un objeto de la clase HeroeDto.
 * @return El objeto de la clase HeroeDto que hemos convertido.
 * @author Natalia González
 * @since 1.0
 */
fun Heroe.toDto(): HeroeDto {
    return HeroeDto(
        id = this.id.toString(),
        nickname = this.nickname,
        nombre = this.nombre,
        edad = this.edad.toString(),
        vivo = this.vivo.toString(),
        villano = this.villano.toString(),
        habilidad = this.habilidad,
        pc = this.pc.toString()
    )
}
