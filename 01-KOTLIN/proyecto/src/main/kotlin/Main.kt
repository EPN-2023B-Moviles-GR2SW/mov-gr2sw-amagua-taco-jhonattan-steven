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
    //ARREGLO ESTATICO
    //No se puede ni aumentar ni disminuir
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Aregglo Dinamico
    //cambia la cantidad de elementos del arreglo se puede aumentar o disminuor
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    val respuestaForEach: Unit = arregloDinamico.forEach{ valorActual: Int -> println("ValorActual: ${valorActual}") }

    arregloDinamico.forEach{ println("valor actual: ${it}") }

    arregloEstatico.forEachIndexed { index: Int, valorActual:Int ->
        println("Valor ${valorActual} Indice: ${index}")
    }

    println(respuestaForEach)
    //MAP -> Nuta el arreglo(Cambia el arreglo)
    //1) enviamos el nuevo valor de la iteracion
    //2) Nos devuelve ES UN NUEVO ARREGLO
    // con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual:Int -> return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //FIlter ->filtra el arreglo
    //1) devuelve una expresion (TRUE o FALSE)
    //2) devuelve un nuevo artreglo Filtrado

    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual: Int -> val mayoresACinco: Boolean = valorActual>5
        return@filter mayoresACinco
    }

    println(respuestaFilter)

    val respuestaFilterDos = arregloDinamico.filter { it<=5 }

    println(respuestaFilterDos)

    //OR AND
    //Or -> Any (alguna cumple?)
    //and -> all (todos cumplen?)
    // alguno es mayor que 5
    val respuestaAny: Boolean = arregloDinamico.any{valorActualL: Int -> return@any(valorActualL>5)}
    println(respuestaAny)

    // son todos los elementos de este arreglo amyotes que 5}// son todos los elementos de este arreglo amyotes que 5
    val respuestaAll: Boolean = arregloDinamico.all { valorActual: Int -> return@all(valorActual>5)}
    println(respuestaAll)

    //Reduce -> valor acumulado
    //Ayuda a acumular valores
    // el valor acumulado en kotlin siempre va a iniciar en 0
    // [1,2,3,4,5] -> Sumeme todos los valores del arreglo
    //valorIteracion1: valorEmpieza    + 1 = 0+1= 1   ->Iteracion1
    //valorIteracion2: valorIteracion1 + 2 = 1+2 =3   ->Iteracion2
    //valorIteracion3: valorIteracion2 + 3 = 3+3=6    ->Iteracion3
    //valorIteracion4: valorIteracion3 + 4 = 6+4=10   ->Iteracion4
    //valorIteracion5: valorIteracion4 + 5 = 10+5=15  ->Iteracion5
    val respuestaReduce: Int = arregloDinamico.reduce{valorAcumulado:Int, valorActual: Int ->
        return@reduce (valorAcumulado+valorActual)
    }
    println(respuestaReduce)//78
    //para un carro de compras la logica seria
    //valorAcumulado+(precioItem * cantidadDeItems)

    println("Program arguments: ${args.joinToString()}")
}
//COMMENT
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