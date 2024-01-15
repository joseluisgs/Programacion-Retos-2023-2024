package org.example.models

/**
 * Clase que representa una celda o casilla de la mazmorra
 * El tablero del juego está formado por objetos Celda, compuestos a su vez por un objeto de tipo Any, para contener
 * otros objetos de cualquiera de las clases incluidas en la aplicación, ya sea un personaje o un horrocrux.
 * Si no contiene nada, se le asignará el valor null.
 * Además, tendremos un flag que nos ayudará a saber si hemos pasado por la celda o no.
 * @property contenido Guardará el objeto que haya en la celda
 * @property visitada Booleano al que asignaremos el valor true si hemos pasado por la celda, false si no hemos pasado aún
 * @property iconoSuelo Icono que representará visualmente la celda visitada pero vacía
 * @property iconoOscuridad Icono que representará visualmente la celda si no la hemos visitado aún
 */

data class Celda(
    var contenido:Any? = null,
    var visitada:Boolean = false,
){
    val iconoSuelo:String = "⬜"
    val iconoOscuridad:String = "⬛"
}
