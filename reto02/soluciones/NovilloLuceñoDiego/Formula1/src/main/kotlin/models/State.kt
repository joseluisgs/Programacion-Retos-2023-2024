package org.example.models

/**
 * Una clase que incluye "downtimeleft" que es la cantidad de tiempo que cada piloto debe estar parado
 * y "dnf" que si es true, significa que no pueden terminar la carrera
 */
data class State (var downtimeleft : Int,
                  var dnf : Boolean = false)