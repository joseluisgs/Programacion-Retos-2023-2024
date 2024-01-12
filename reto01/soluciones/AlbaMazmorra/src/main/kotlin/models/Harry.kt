package org.example.models
/**
 * Clase base que representa al personaje principal Harry en la aventura mágica.
 *
 * @property salud Puntos de salud de Harry.
 * @property horrocrux Cantidad de horrocruxes recolectados por Harry.
 */
    open class Harry(
        var salud: Int = 100,
        var horrocrux: Int = 0
    ) {
        /**
         * Posición actual de Harry en la mazmorra, representada por un array de dos elementos [fila, columna].
         */
        var posicion: Array<Int> = Array (2) { 0 }
        private var fila: Int = posicion[0]
        private var columna: Int = posicion [1]

        /**
         * Incrementa la cantidad de Horrocruxes recolectados por Harry en uno y muestra un mensaje.
         */
        fun recogerHorrocrux() {
           horrocrux++
            println("Recogemos un horrocrux. Ahora tenemos ${horrocrux} horrocruxes")
        }
        /**
         * Obtiene la fila actual de Harry en la mazmorra.
         *
         * @return Valor de la fila actual.
         */

        fun obtenerFila(): Int {
            return fila
        }
        /**
         * Obtiene la columna actual de Harry en la mazmorra.
         *
         * @return Valor de la columna actual.
         */

        fun obtenerColumna(): Int {
            return columna
        }


    }
