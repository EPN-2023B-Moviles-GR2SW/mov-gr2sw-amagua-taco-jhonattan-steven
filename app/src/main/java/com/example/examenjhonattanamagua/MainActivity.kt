package com.ExamenJhonattanAmagua

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.activities.CreateAutorActivity
import com.example.examenjhonattanamagua.activities.LibroActivity
import com.example.examenjhonattanamagua.activities.UpdateAutorActivity
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.db.SqliteHelperLibro
import com.example.examenjhonattanamagua.db.SqliteHelperAutor
import com.example.examenjhonattanamagua.models.Autor

class MainActivity : AppCompatActivity() {
    private var autors: ArrayList<Autor>? = null
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DB.autor = SqliteHelperAutor(this)
        DB.libro = SqliteHelperLibro(this)



        loadAutores()

        val btnCreate = findViewById<Button>(
            R.id.btn_autores
        )

        btnCreate.setOnClickListener {
            goToActivity(CreateAutorActivity::class.java)
        }
    }


    override fun onResume() {
        super.onResume()
        loadAutores()
    }

    private fun loadAutores() {
        val listView = findViewById<ListView>(
            R.id.lv_autores
        )
        autors = DB.autor!!.getAll()
        println(autors)
        if (autors != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                autors!!
            )
            listView.adapter = adapter

            adapter.notifyDataSetChanged()
            registerForContextMenu(listView)
        }

    }


    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }

    private fun showConfirmDeleteDialog(autor: Autor) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Estás seguro de eliminar: ${autor.nombre}?")
        builder.setMessage("Una vez eliminado no se podrá recuperar.")
        builder.setPositiveButton("Sí") { _, _ ->
            val removed = autors!!.removeAt(selectedItemId)
            DB.autor!!.remove(removed.id)
            loadAutores()
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
        inflater.inflate(R.menu.menu_autores, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val autor = autors!![selectedItemId]
        return when(item.itemId) {
            R.id.mi_show_series -> {
                "Hacer algo con: ${selectedItemId}"
                if (autors == null) return false

                val params = Bundle()
                params.putString("id", autor.id)
                params.putString("nombre", autor.nombre)
                params.putString("nacionalidad", autor.nacionalidad)
                params.putInt("anioNacimiento", autor.anioNacimiento)
                params.putBoolean("tienePremioNobel", autor.premioNobel)

                goToActivity(LibroActivity::class.java, params)
                return true
            }
            R.id.mi_update -> {
                "Hacer algo con: ${selectedItemId}"
                val params = Bundle()
                params.putString("id", autor.id)
                params.putString("nombre", autor.nombre)
                params.putString("nacionalidad", autor.nacionalidad)
                params.putInt("anioNacimiento", autor.anioNacimiento)
                params.putBoolean("tienePremioNobel", autor.premioNobel)

                goToActivity(UpdateAutorActivity::class.java, params)
                return true
            }
            R.id.mi_delete -> {
                showConfirmDeleteDialog(autor)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}