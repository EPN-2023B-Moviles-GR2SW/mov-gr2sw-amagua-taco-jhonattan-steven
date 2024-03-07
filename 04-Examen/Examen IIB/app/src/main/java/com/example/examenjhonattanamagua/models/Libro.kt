package com.example.examenjhonattanamagua.models


class Libro (
    var id: String,
    var titulo: String,
    var autorId: String,
    var fechaPublicacion: String,
    var numeroPaginas: Long,
    var precio: Double
){
    constructor(): this("","","","",0,0.0)

    override fun toString(): String {
        return "$titulo"
    }

}