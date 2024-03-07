package com.example.examenjhonattanamagua.db

import com.example.examenjhonattanamagua.dtos.LibroDto
import com.example.examenjhonattanamagua.models.Libro
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LibroFirestore {
    val db = Firebase.firestore

    companion object {
        fun createSeriesFromDocument(document: QueryDocumentSnapshot): Libro {
            val id = document.id
            val titulo = document.data["titulo"] as String?
            val fechaPublicacion = document.data["fechaPublicacion"] as String?
            val numeroPagina = document.data["numeroPagina"] as Long?
            val precio = document.data["precio"] as Double?
            val autorId = document.data["autorId"] as String?

            if (id == null || titulo == null || fechaPublicacion == null || numeroPagina == null || precio == null || autorId == null) {
                return Libro()
            }

            return Libro(id, titulo, autorId, fechaPublicacion, numeroPagina.toLong(), precio.toDouble())
        }

        fun createSeriesFromDocument(document: DocumentSnapshot): Libro {
            val id = document.id
            val titulo = document.data?.get("titulo") as String?
            val fechaPublicacion = document.data?.get("fechaPublicacion") as String?
            val numeroPagina = document.data?.get("numeroPagina") as Int?
            val precio = document.data?.get("precio") as Double?
            val autorId = document.data?.get("autorId") as String?

            if (id == null || titulo == null || fechaPublicacion == null || numeroPagina == null || precio == null || autorId == null) {
                return Libro()
            }

            return Libro(id, titulo, autorId, fechaPublicacion, numeroPagina.toLong(), precio.toDouble())
        }
    }

    fun getAllByAutor(autorId: String): Task<QuerySnapshot> {
        return db.collection("libros")
            .whereEqualTo("autorId", autorId)
            .get()
    }

    fun create(data: LibroDto): Boolean {
        val libroData = hashMapOf(
            "titulo" to data.titulo,
            "fechaPublicacion" to data.fechaPublicacion,
            "numeroPagina" to data.numeroPaginas,
            "precio" to data.precio,
            "autorId" to data.autorId
        )
        var result = false
        db.collection("libros")
            .add(libroData)
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }

    fun update(id: String, data: LibroDto): Boolean {
        val seriesData = hashMapOf(
            "titulo" to data.titulo,
            "fechaPublicacion" to data.fechaPublicacion,
            "numeroPagina" to data.numeroPaginas,
            "precio" to data.precio,
            "autorId" to data.autorId
        )
        var result = false
        db.collection("libros")
            .document(id)
            .set(seriesData)
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }

    fun remove(id: String): Boolean {
        var result = false
        db.collection("libros")
            .document(id)
            .delete()
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }
}