package org.example.controller

enum class Direction (val X : Int, val Y: Int, val directionname : String){
    North(X = -1, Y = 0, directionname = "North"),
    South(X = 1, Y = 0, directionname = "South"),
    West(X = 0, Y = -1, directionname = "West"),
    East(X = 0, Y = 1, directionname = "East"),
}