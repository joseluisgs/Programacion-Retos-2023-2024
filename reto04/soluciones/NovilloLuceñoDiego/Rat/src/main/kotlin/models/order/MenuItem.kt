package org.example.models.order

import java.util.UUID

data class MenuItem (
    val uuid: UUID = UUID.randomUUID(),
    var overallRating : Int,
    var price : Int,
    val name : String,
)
