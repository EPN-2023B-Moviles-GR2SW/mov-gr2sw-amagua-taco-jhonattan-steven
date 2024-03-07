package com.example.examenjhonattanamagua.db

import com.example.examenjhonattanamagua.dtos.AutorDto
import com.example.examenjhonattanamagua.models.Autor
import com.example.examenjhonattanamagua.models.Libro
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AutorFirestore {
    val db = Firebase.firestore

    companion object {
        fun createAutorFromDocument(document: QueryDocumentSnapshot): Autor {
            val id = document.id
            val name = document.data["nombre"] as String?
            val anioNacimiento = document.data["anioNacimiento"] as Long?
            val nacionalidad = document.data["nacionalidad"] as String?
            val premioNobel = document.data["premioNobel"] as Boolean?
            val series = mutableListOf<Libro>()

            if (id == null || name == null || anioNacimiento == null || nacionalidad == null || premioNobel == null) {
                return Autor()
            }

            return Autor(id, name, anioNacimiento , nacionalidad, premioNobel)
        }

        fun createAutorFromDocument(document: DocumentSnapshot): Autor {
            val id = document.id
            val name = document.data?.get("nombre") as String?
            val anioNacimiento = document.data?.get("anioNacimiento") as Long?
            val nacionalidad = document.data?.get("nacionalidad") as String?
            val premioNobel = document.data?.get("premioNobel") as Boolean?
            val series = mutableListOf<Libro>()

            if (id == null || name == null || name == null || anioNacimiento == null || nacionalidad == null || premioNobel == null) {
                return Autor()
            }

            return Autor(id, name, anioNacimiento, nacionalidad, premioNobel)
        }
    }

    fun getAll(): Task<QuerySnapshot> {
        return db.collection("autores")
            .get()
    }

    fun getOne(id: String): Task<DocumentSnapshot> {
        return db.collection("autores")
            .document(id)
            .get()
    }

    fun create(autor: AutorDto) {
        val autorData = hashMapOf(
            "nombre" to autor.nombre,
            "anioNacimiento" to autor.anioNacimiento,
            "nacionalidad" to autor.nacionalidad,
            "premioNobel" to autor.premioNobel
        )

        db.collection("autores")
            .add(autorData)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }

    fun update(id: String, autor: AutorDto) {
        val autorData = hashMapOf(
            "nombre" to autor.nombre,
            "anioNacimiento" to autor.anioNacimiento,
            "nacionalidad" to autor.nacionalidad,
            "premioNobel" to autor.premioNobel
        )

        db.collection("autores")
            .document(id)
            .set(autorData)
            .addOnSuccessListener {
                println("DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                println("Error updating document: $e")
            }
    }

    fun remove(id: String) {
        db.collection("autores")
            .document(id)
            .delete()
            .addOnSuccessListener {
                println("DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                println("Error deleting document: $e")
            }
    }

}