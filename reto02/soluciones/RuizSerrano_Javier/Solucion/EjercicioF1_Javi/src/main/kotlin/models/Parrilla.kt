package org.example.models

import java.time.LocalTime


abstract class Parrilla{
    open var nombre=""
    open var posicion= Array(2){0}
    open val fila = 0
    open var columna = 0
    open var vuelta=0
    open var pit1=(1..3).random()
    open var pit2=(1..3).random()
    open var pit3=(1..3).random()
    var inicio: LocalTime = LocalTime.now()
    var final: LocalTime = LocalTime.now()
    var dnf=false

}