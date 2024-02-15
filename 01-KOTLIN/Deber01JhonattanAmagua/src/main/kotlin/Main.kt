import java.io.*
import java.util.Date

fun main() {
    val autor = Autor("Gabriel Garcia Marquez", 1927, "Colombiano", true)
    val libro = Libro("Cien Años de Soledad", autor, Date(), 417, 29.99)
    val libro2 = Libro("Cien Años de Soledad Segunda Parte", autor, Date(), 201, 39.00)
    autor.libros.add(libro)
    autor.libros.add(libro2)


    // Guardar datos
    autor.guardar()
    libro.guardar()
    libro2.guardar()

    val respuestaForEach : Unit = autor.libros.forEachIndexed{index: Int, valorActual: Libro ->
        println("\n Index: ${index+1} ${valorActual.titulo}")
    }



    // Actualizar el libro
    val libroActualizado = leerLibro("Cien_Años_de_Soledad.dat")
    libroActualizado?.precio = 35.99
    libroActualizado?.let {
        actualizarLibro("Cien_Años_de_Soledad", it)
    }

    println("Libro acturalizado: \n "+libro.titulo +" "+ libro.autor.nombre+" Precio:"+libro.precio)


    eliminarLibro("Cien_Años_de_Soledad")
    autor.libros.remove(libro)
    println("Se elimino un libro")

    val respuestaForEach2 : Unit = autor.libros.forEachIndexed{index: Int, valorActual: Libro ->
        println("\n Index: ${index+1} ${valorActual.titulo}")
    }


    val autorLeido = leerAutor("Gabriel Garcia Marquez.dat")
    println("Autor: ${autorLeido?.nombre}, Libros: ${autorLeido?.libros?.size}")
}

class Autor(
    var nombre: String,
    var anioNacimiento: Int,
    var nacionalidad: String,
    var premioNobel: Boolean,
    var libros: ArrayList<Libro> = arrayListOf()
) : Serializable
//hola

class Libro(
    var titulo: String,
    @Transient var autor: Autor, // Transient para evitar serialización cíclica
    var fechaPublicacion: Date,
    var numeroPaginas: Int,
    var precio: Double
) : Serializable


fun Autor.guardar() {
    ObjectOutputStream(FileOutputStream("$nombre.dat")).use { it.writeObject(this) }
}


fun Libro.guardar() {
    ObjectOutputStream(FileOutputStream("${titulo.replace(" ", "_")}.dat")).use { it.writeObject(this) }
}


fun leerAutor(nombreArchivo: String): Autor? {
    return ObjectInputStream(FileInputStream(nombreArchivo)).use { it.readObject() as Autor }
}


fun leerLibro(tituloArchivo: String): Libro? {
    return ObjectInputStream(FileInputStream(tituloArchivo)).use { it.readObject() as Libro }
}


fun actualizarLibro(tituloOriginal: String, libroActualizado: Libro) {
    File("${tituloOriginal.replace(" ", "_")}.dat").delete()
    libroActualizado.guardar()
}


fun eliminarLibro(titulo: String) {
    File("${titulo.replace(" ", "_")}.dat").delete()

}


