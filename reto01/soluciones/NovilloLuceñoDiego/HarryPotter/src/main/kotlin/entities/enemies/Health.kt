package org.example.entities.enemies

import org.example.entities.allies.Ally
import org.example.objects.Entity

class Health (var value : Int) {
    fun takedmg(attacker : Entity){
        if (attacker is Enemy){
            value -= attacker.attack
            println("${attacker.name} attacked Harry!")
        }else{
            value -= 10
            println("Rons spell backfired...")
        }
    }

    fun heal (ally: Ally){
        value += ally.healingpower
        println("${ally.name} has healed Harry!")
    }
}