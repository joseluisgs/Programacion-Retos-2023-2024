package org.example.models

/**
 * Clase que representa una posición de la mazmorra, mediante una fila y una columna
 * @property columna coordenada "x" de la posición guardada
 * @property fila coordenada "y" de la posición guardada
 */
data class Posicion(var fila: Int = 0, var columna: Int = 0)