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
//para instanciar ya no es necesario la palabra new
    val sumaUno = Suma (1, 1)
    val sumaDos = Suma (null, 1)
    val sumaTres = Suma (1, null)
    val sumaCuatro =Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
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
        //: ConstructorClasePadre (Parametro1,parametro2)
    init { // Bloque codigo constructor primario
        this.numeroUno
        this.numeroDos
    }
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
    //cuarto constructot
    constructor(uno: Int?, dos: Int?): this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else uno,
    )
    fun sumar(): Int{
        val total = numeroUno+ numeroDos
        //Suma.agregarHistorial(total) Cuando se esta fuera de la clase es necesario especificar de que clase es
        agregarHistorial(total)
        return total
    }
    //En kotlin tanto metodos como propiedades son por defecto publico
    //static
    // Un metodo statico era para usar sin implementar una instancia
    //Singleto es que solo exista una unica instancia de la clase
    //en Kotlin es posible realizar en el companion object declarar atributos y metodos compartidos en todas las instancias de la clase
    companion object{
        val pi=3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

}
//void -> Unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}
//last comment
fun calcularSueldo (
    sueldo: Double,// paramaetro requerido
    tasa: Double= 12.0,//parametroOpciona(defecto) ES DECIR Si no se pone ningun valor, se asigna por defecto en este caso el valor de 12
    bonoEspecial: Double?=null, //Permite que esta variable pueda ser o Double o puede tener el valor null, y esta variable nos obliga a diferenciar es decir no es
    //posible operar asumiendo que siempre sera doble porque en algun momento puede ser nulo y generaria error
    ): Double{
    if(bonoEspecial==null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa) + bonoEspecial
    }
}