package org.example.models

import org.example.controllers.Restaurante

interface Colocarse {
    fun colocar(restaurante: Array<Array<Any?>>): Array<Array<Any?>>
}