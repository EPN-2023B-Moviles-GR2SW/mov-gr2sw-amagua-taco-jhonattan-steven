package com.example.examenjhonattanamagua.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examenjhonattanamagua.dtos.AutorDto
import com.example.examenjhonattanamagua.models.Autor

class SqliteHelperAutor(
    context: Context?
): SQLiteOpenHelper(
    context,
    "autores",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCreateAutoresTable =
            """
                CREATE TABLE AUTORES(
                    ID VARCHAR(40) PRIMARY KEY,
                    NAME VARCHAR(40),
                    ANIO_NACIMIENTO VARCHAR(40),
                    NACIONALIDAD VARCHAR(40),
                    PREMIO_NOBEL INTEGER
                )
            """.trimIndent()

        db?.execSQL(scriptCreateAutoresTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Code
    }

    // generate an id
//    private fun generateId(): String {
//        val id = (0..1000000).random()
//        return id.toString()
//    }
//
//    fun getAll(): ArrayList<Autor> {
//        val readableDatabase = readableDatabase
//
//        val script = "SELECT * FROM AUTORES".trimIndent()
//        val result = readableDatabase.rawQuery(
//            script,
//            null
//        )
//        // logica busqueda
//        val exists = result.moveToFirst()
//
//        val autors = arrayListOf<Autor>()
//        if (exists) {
//            do {
//                val id = result.getString(0) // Columna indice 0 -> Id
//                val name = result.getString(1) // Columna indice 1 -> name
//                val anioNacimiento = result.getString(2).toInt() // Columna indice 2 -> anioNacimiento
//                val nacionalidad = result.getString(3) // Columna indice 3 -> Nacionalidad
//                val premioNobel = result.getInt(4) == 1 // Columna indice 3 -> PremioNobel
//
//                if (id != null) {
//                    val autor = Autor(id, name, anioNacimiento, nacionalidad, premioNobel)
//                    autors.add(autor)
//                }
//            } while (result.moveToNext())
//        }
//
//        result.close()
//        readableDatabase.close()
//
//        return autors
//    }
//
//    fun getOne(id: String): Autor {
//        val readableDatabase = readableDatabase
//
//        val script = "SELECT * FROM AUTORES WHERE ID = ?".trimIndent()
//        val result = readableDatabase.rawQuery(
//            script,
//            arrayOf(id.toString())
//        )
//        // logica busqueda
//        val exists = result.moveToFirst()
//
//        val found = Autor("", "", 0, "", true,)
//        if (exists) {
//            do {
//                val id = result.getString(0)
//                val name = result.getString(1)
//                val anioNacimiento = result.getString(2).toInt()
//                val nacionalidad = result.getString(3)
//                val premioNobel = result.getInt(4) == 1
//
//                if (id != null) {
//                    found.id = id
//                    found.nombre = name
//                    found.anioNacimiento = anioNacimiento
//                    found.nacionalidad = nacionalidad
//                    found.premioNobel = premioNobel
//                }
//            } while (result.moveToNext())
//        }
//
//
//        result.close()
//        readableDatabase.close()
//
//        return found
//    }
//
//    fun create(data: AutorDto): Boolean {
//        val writableDatabase = writableDatabase
//
//        val values = ContentValues()
//
//        values.put("ID", generateId())
//        values.put("NAME", data.nombre)
//        values.put("ANIO_NACIMIENTO", data.anioNacimiento.toString())
//        values.put("NACIONALIDAD", data.nacionalidad)
//        values.put("PREMIO_NOBEL", data.premioNobel)
//
//        val result = writableDatabase.insert(
//            "AUTORES",
//            null,
//            values
//        )
//
//        writableDatabase.close()
//
//        return result.toInt() != -1
//    }
//
//    fun update(id: String, data: AutorDto): Boolean {
//        val writableDatabase = writableDatabase
//
//        val values = ContentValues()
//
//        values.put("NAME", data.nombre)
//        values.put("ANIO_NACIMIENTO", data.anioNacimiento.toString())
//        values.put("NACIONALIDAD", data.nacionalidad)
//        values.put("PREMIO_NOBEL", data.premioNobel)
//
//        val result = writableDatabase.update(
//            "AUTORES",
//            values,
//            "ID = ?",
//            arrayOf(id.toString())
//        )
//
//        writableDatabase.close()
//
//        return result != -1
//    }
//
//    fun remove(id: String): Boolean {
//        val writableDatabase = writableDatabase
//
//        val result = writableDatabase.delete(
//            "AUTORES",
//            "ID = ?",
//            arrayOf(id.toString())
//        )
//
//        writableDatabase.close()
//
//        return result != -1
//    }
}