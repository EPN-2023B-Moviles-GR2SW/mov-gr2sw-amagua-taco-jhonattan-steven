package com.example.examenjhonattanamagua.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.db.AutorFirestore
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.db.LibroFirestore
import com.example.examenjhonattanamagua.models.Autor
import com.example.examenjhonattanamagua.models.Libro

class LibroActivity : AppCompatActivity() {

    private var libros: ArrayList<Libro>? = null
    var selectedAutorId = ""
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro)

        selectedAutorId = intent.getStringExtra("id").toString()

        // load series
//        loadLibros(selectedLibroId)



        // Buttons and Listeners
        val goBackButton = findViewById<ImageButton>(
            R.id.btn_go_back_to_autores
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val createSucursalButton = findViewById<Button>(
            R.id.btn_create_libro
        )

        createSucursalButton.setOnClickListener {
            DB.autor!!.getOne(selectedAutorId)
                .addOnSuccessListener {
                    val bundle = Bundle()
                    val autor = AutorFirestore.createAutorFromDocument(it)
                    println("libro activity")
                    println(autor.id)
                    println(selectedAutorId)
                    if (autor.id == selectedAutorId) {
                        println("setting bundle")
                        bundle.putString("libroId", selectedAutorId)
                        bundle.putString("autorId", selectedAutorId)
                        bundle.putString("autorName", autor.nombre)
                        println(bundle)
                    }
                    goToActivity(CreateLibroActivity::class.java, bundle)
                }
        }

    }

    override fun onResume() {
        super.onResume()

        loadLibros(selectedAutorId)
    }

    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }

    private fun loadLibros(autorId: String) {
        if (autorId != "") {
            libros = arrayListOf<Libro>()
            val autor = Autor()
            println("autorId " + autorId)
            DB.autor!!.getOne(autorId)
                .addOnSuccessListener {
                    val foundAutor = AutorFirestore.createAutorFromDocument(it)
                    println("foundAutor " + autorId)
                    println(foundAutor.id)
                    autor.id = foundAutor.id
                    autor.nombre = foundAutor.nombre
                    autor.nacionalidad = foundAutor.nacionalidad
                    autor.premioNobel = foundAutor.premioNobel
                    autor.anioNacimiento = foundAutor.anioNacimiento

                    if (autor.id == autorId) {
                        val tvTitle = findViewById<TextView>(R.id.tv_autor)
                        println("autor nombre" + autor.nombre)
                        tvTitle.text = autor.nombre
                        DB.libro!!.getAllByAutor(autorId)
                            .addOnSuccessListener {documents ->
                            for (document in documents) {
                                println(document)
                                libros!!.add(LibroFirestore.createSeriesFromDocument(document))
                            }
                            val sucursalesList = findViewById<ListView>(R.id.lv_libros)
                            val adapter = ArrayAdapter(
                                this,
                                android.R.layout.simple_list_item_1,
                                libros!!
                            )
                            sucursalesList.adapter = adapter
                            adapter.notifyDataSetChanged()
                            registerForContextMenu(sucursalesList)
                        }
                    }
                }
        }
    }

    private fun showConfirmDialog(libro: Libro) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Deseas eliminar el libro de: ${libro.titulo}?")
        builder.setMessage("Una vez eliminado, no lo podrás recuperar")
        builder.setPositiveButton("Sí, eliminar") { dialog, which ->
            DB.libro!!.remove(libro.id)
            loadLibros(selectedAutorId)
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(
            R.menu.menu_libros,
            menu
        )

        // position
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedLibro = libros!![selectedItemId]
        return when(item.itemId) {
            R.id.mi_edit_series -> {
                val autor = DB.autor!!.getOne(selectedAutorId)
                    .addOnSuccessListener {
                        val autor = AutorFirestore.createAutorFromDocument(it)
                        goToActivity(
                            UpdateLibroActivity::class.java,
                            Bundle().apply {
                                putString("autorId", selectedAutorId)
                                putString("autorName", autor.nombre)
                                putString("libroId", selectedLibro.id)
                                putString("titulo", selectedLibro.titulo)
                                putString("fechaPublicacion", selectedLibro.fechaPublicacion)
                                putLong("numeroPaginas", selectedLibro.numeroPaginas)
                                putDouble("precio", selectedLibro.precio)
                            }
                        )
                    }
                true
            }
            R.id.mi_delete_series -> {
                showConfirmDialog(selectedLibro)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}