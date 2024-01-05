package org.example.controller

class Position (
    val X : Int,
    val Y : Int
){
    fun isValidGoing(direction: Direction, dungeon : Board) : Boolean{
        val newxposition = X + direction.X
        val newyposition = Y + direction.Y

        return !((newyposition < 0 || newxposition < 0) ||
            (newyposition >= dungeon.gameBoard.size || newxposition >= dungeon.gameBoard.size))
    }
}