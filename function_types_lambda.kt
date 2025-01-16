//todas las funciones en kotlin son consideradas constructores first-class
//lo que significa que son tratadas como data types por lo tanto se pueden almacenar en variables
//pasarse como argumentos o incluso ser retornadas
//se pueden declarar como function literals, lo que se conoce como lambda expressions o coloquialmente lambda
fun main() {
    //para almacena una funcion se puede usar el function reference operator
    val trickFunction = ::trick
    //cuando se asigna una expresion lambda no es necesario usar el function reference operator
    val treatFunction = treat
    val sayHelloFunction = sayHello
    sayHelloFunction()
    
    //podemos asignar las funciones desde otra funcion
    val trickFunctionSelected = trickOrTreat(true)
    val treatFunctionSelected = trickOrTreat(false)
    //llamada a la expresion lambda
    trickFunctionSelected()
    treatFunctionSelected()
}

//una funcion que puede ser almacenada en una varable
fun trick() {
    println("no treats!")
}

//para hacerlo mas conciso se puede usar una expresion lambda

val treat = {
    println("no tricks!")
}

//las expresiones lambda pueden tener parametros y valores de retorno, como en la siguiente expresion
//val nombreDeLafuncion: (params) -> return type = {...}
val sayHello: () -> Unit = {
    println("Hello Kotlin")
}

//las funciones tambien se pueden retornar desde otras funciones
//los parametros van en la funcion y no en la lambda expression
fun trickOrTreat(isTrick: Boolean): () -> Unit {
    if(isTrick){
        return ::trick
    }else{
        return treat
    }
}