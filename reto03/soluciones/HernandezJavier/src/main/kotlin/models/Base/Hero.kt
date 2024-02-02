package org.example.models.Base

import java.time.LocalDateTime

open class Hero(
    var id: Int = 0,
    var nombre: String,
    var alias: String,
    var altura: Int,
    var edad: Int,
    var notas: String,
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var createdAt: LocalDateTime = LocalDateTime.now())
