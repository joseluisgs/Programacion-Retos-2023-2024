package org.example.models

class Marvel(
    id:Int = 0,
    nombre:String = "",
    alias:String = "",
    altura:Int = 180,
    notas:String =""
):SuperHero(id,nombre, alias, altura, notas)