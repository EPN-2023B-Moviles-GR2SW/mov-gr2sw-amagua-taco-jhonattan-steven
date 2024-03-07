package com.example.examenjhonattanamagua.models

class Autor(
    var id: String,
    var nombre: String,
    var anioNacimiento: Long,
    var nacionalidad: String,
    var premioNobel: Boolean,
){
    constructor():this("", "",0,"", true)

    override fun toString(): String {
        return this.nombre
    }
}