package org.example.models

abstract class Enemigo: Personaje() {
    abstract fun atacar() :Boolean
}
