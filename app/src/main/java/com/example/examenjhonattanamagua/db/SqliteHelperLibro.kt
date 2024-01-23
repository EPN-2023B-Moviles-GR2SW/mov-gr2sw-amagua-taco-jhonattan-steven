package com.example.examenjhonattanamagua.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examenjhonattanamagua.dtos.LibroDto
import com.example.examenjhonattanamagua.models.Libro

class SqliteHelperLibro(
    context: Context
): SQLiteOpenHelper(
    context,
    "libros",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCreateLibrosTable =
            """
                CREATE TABLE LIBROS(
                    ID VARCHAR(40) PRIMARY KEY,
                    TITULO VARCHAR(60),
                    FECHA_PUBLICACION VARCHAR(60),
                    NUMERO_PAGINAS INTEGER,
                    PRECIO REAL,
                    AUTOR_ID VARCHAR(40),
                    FOREIGN KEY(AUTOR_ID) REFERENCES AUTORES
                )
            """.trimIndent()

        db?.execSQL(scriptCreateLibrosTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Code
    }

    // generate an id
    private fun generateId(): String {
        val id = (0..1000000).random()
        return id.toString()
    }

    fun getAll(): ArrayList<Libro> {
        val readableDatabase = readableDatabase

        val script = "SELECT * FROM LIBROS".trimIndent()
        val result = readableDatabase.rawQuery(
            script,
            null
        )
        // logica busqueda
        val exists = result.moveToFirst()

        val allLibros = arrayListOf<Libro>()
        if (exists) {
            do {
                val id = result.getString(0)
                val titulo = result.getString(1)
                val fechaPublicacion = result.getString(2)
                val numeroPaginas = result.getInt(3)
                val precio = result.getDouble(4)
                val autorId = result.getString(5)

                if (id != null) {
                    val libros = Libro(
                        id,
                        titulo,
                        autorId,
                        fechaPublicacion,
                        numeroPaginas,
                        precio,

                    )
                    allLibros.add(libros)
                }
            } while (result.moveToNext())
        }

        return allLibros
    }

    fun getAllByAutor(autorId: String): ArrayList<Libro> {
        val readableDatabase = readableDatabase

        val script = "SELECT * FROM LIBROS WHERE AUTOR_ID = ?".trimIndent()

        val result = readableDatabase.rawQuery(
            script,
            arrayOf(autorId.toString())
        )

        val exists = result.moveToFirst()

        val allLibros = arrayListOf<Libro>()

        if (exists) {
            do {
                val id = result.getString(0)
                val titulo = result.getString(1)
                val fechaPublicacion = result.getString(2)
                val numeroPaginas = result.getInt(3)
                val precio = result.getDouble(4)
                val autorId = result.getString(5)

                if (id != null) {
                    val libros = Libro(
                        id,
                        titulo,
                        autorId,
                        fechaPublicacion,
                        numeroPaginas,
                        precio
                    )
                    allLibros.add(libros)
                }
            } while (result.moveToNext())
        }

        result.close()
        readableDatabase.close()

        return allLibros
    }

    fun create(data: LibroDto): Boolean {
        val writableDatabase = writableDatabase

        val values = ContentValues()

        values.put("ID", generateId())
        values.put("TITULO", data.titulo)
        values.put("FECHA_PUBLICACION", data.fechaPublicacion)
        values.put("NUMERO_PAGINAS", data.numeroPaginas)
        values.put("PRECIO", data.precio)
        values.put("AUTOR_ID", data.autorId)

        val result = writableDatabase.insert(
            "LIBROS",
            null,
            values
        )

        writableDatabase.close()

        return result.toInt() != -1
    }

    fun update(id: String, changes: LibroDto): Boolean {
        val writableDatabase = writableDatabase

        val values = ContentValues()

        values.put("TITULO", changes.titulo)
        values.put("FECHA_PUBLICACION", changes.fechaPublicacion)
        values.put("NUMERO_PAGINAS", changes.numeroPaginas)
        values.put("PRECIO", changes.precio)

        val result = writableDatabase.update(
            "LIBROS",
            values,
            "ID = ?",
            arrayOf(id.toString())
        )

        writableDatabase.close()

        return result != -1
    }

    fun remove(id: String): Boolean {
        val writableDatabase = writableDatabase

        val result = writableDatabase.delete(
            "LIBROS",
            "ID = ?",
            arrayOf(id.toString())
        )

        writableDatabase.close()

        return result != -1
    }
}