package org.example.controllers

interface DbOperaciones <T> {
    fun getAll(): Array<T>
    fun getById(id: Int): T
    fun getByName(name:String): T
    fun save(sH: T): T
    fun update(id: Int, sH: T): T
    fun delete(id: Int): T
    fun sortedById(): Array<T>
    fun sortedByName(): Array<T>



}

