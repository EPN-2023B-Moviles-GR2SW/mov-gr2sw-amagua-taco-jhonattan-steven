import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")
    //VARIABLES INMUTABLES
    val inmutable: String = "Vicente";
    //VARIABLES MUTABLES
    var mutable: String= "Vicente";
    //USAR PREFERIBLEMENTE VAL>VAR

    //Duck typing
    var ejemploVariable="Jhonattan Amagua"//asume que es un String
    val edadEjemplo: Int=12;
    ejemploVariable.trim();
    //ejemploVariable  = edadEjemplo esta mal porque las variables no son del; mismo tipo

    //Variable primitiva
    val nombreProfesor="Adrian Eguez";
    val sueldo: Double= 1.2
    val estadoCivil: Char='C'
    val mayorEdad: Boolean = true
    //Clases java
    val fechaNacimiento :Date = Date()


    //Switch
    val estadoCivilWhen ="C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No Sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueto = if (esSoltero) "Si" else "No"


    //void -> Unit

    calcularSueldo (10.00)
    calcularSueldo (10.00, 15.00, 20.00)
    calcularSueldo (10.00, bonoEspecial = 20.00) // Named Parameters
    calcularSueldo (bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // Parametros

    val sumaUno = Suma (1, 1)
    val sumaDos = Suma (null, 1)
    val sumaTres = Suma (1, null)
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){ // Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}
abstract class Numeros( // Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parametro (sin modificador de acceso))
    // private var uno: Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, // Propiedad de la clase protected numeros.numeroDos
    ){
    // var cedula: string = (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init { // bloque constructor primario
        this.numeroUno; this.numeroDos; // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}
class Suma( // Constructor Primario Suma
    unoParametro: Int, // Parametro
    dosParametro: Int, // Parametro
    ): Numeros (unoParametro, dosParametro) { // Extendiendo y mandando los parametros (super)
    init { // Bloque codigo constructor primario
        this.numeroUno
        this.numeroDos
    }
    //add comment
    //commit and push
    constructor( // Segundo constructor
        uno: Int?, // Parametros
        dos: Int // Parametros
    ): this (
        if(uno == null) 0 else uno,
        dos
    )
    constructor( // Tercer constructor
        uno: Int, // Parametros
        dos: Int? // Parametros
    ): this(
        uno,
        if(dos == null) 0 else dos,
    )
}

fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo (
    sueldo: Double,
    tasa: Double= 12.0,
    bonoEspecial: Double?=null, ): Double{
    if(bonoEspecial==null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa) + bonoEspecial
    }
}