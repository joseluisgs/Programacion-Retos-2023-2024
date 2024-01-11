package org.example.models

class Ronald: Aliado() {

    var heal=20
    var dmg=10
    fun liarla(): Boolean {
        if ((1..100).random() < 30) {
            return true
        } else {
            return false
        }
    }
}