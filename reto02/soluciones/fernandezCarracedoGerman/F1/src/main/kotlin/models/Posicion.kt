package org.example.models

/**
 * Clase que representa una posición dentro del circuito, mediante un carril y una columna (tramo de la pista)
 * Por cada carril circulará un Piloto, y avanzará o retrocederá columnas
 * @property columna coordenada "x" de la posición guardada
 * @property carril coordenada "y" de la posición guardada
 * @see Piloto
 * @see Circuito
 */
data class Posicion (val carril:Int = 0, var columna:Int = 0)