package org.example.entities

import org.example.entities.allies.Ally
import org.example.entities.enemies.Enemy

class Health (var value : Int) {
    /**
     * Un getter que se asegura que el valor que se enseña nuncca es menos de 0
     * @return el de la salud de algo
     */
    fun gethealthvalue() : Int{
        if (value < 0) return 0
        return value
    }

    /**
     * Quita salud dependiendo de quien ataque. En caso de ser Ron, quita 10
     * @param attacker la entidad que esta atacando a quien tenga salud, puede ser un enemigo o Ron
     */
    fun takedmg(attacker : Entity){
        if (attacker is Enemy){
            value -= attacker.attack
            println("${attacker.name} attacked Harry!")
        }else{
            value -= 10
            println("Rons spell backfired...")
        }
    }

    /**
     * Añade a la salud el poder que tenga cada amigo
     * @param ally es el objeto que cura a quien sea que tiene la salud
     */
    fun heal (ally: Ally){
        value += ally.healingpower
        println("${ally.name} has healed Harry!")
    }
}