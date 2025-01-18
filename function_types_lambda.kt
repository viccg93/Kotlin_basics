//todas las funciones en kotlin son consideradas constructores first-class
//lo que significa que son tratadas como data types por lo tanto se pueden almacenar en variables
//pasarse como argumentos o incluso ser retornadas
//se pueden declarar como function literals, lo que se conoce como lambda expressions o coloquialmente lambda
fun main() {
    val extraCoins: (Int) -> String = {
        //quantity toma el valor del parametro que se pasa a la lambda
        //no hay return porque una lambda retorna el resultado de la ultima expresion en ella
        quantity -> "$quantity quarters"
    }
    
    //cuando una lambda tiene solo un parametro y no se nombra, se puede retirar el parametro del cuerpo de la lambda
    //ya que kotlin le asigna implicitamente el nombre de it
    val extraCupcakes: (Int) -> String = {
        "$it cupcakes"
    }
    //para almacenar una funcion se puede usar el function reference operator
    val trickFunction = ::trick
    //cuando se asigna una expresion lambda no es necesario usar el function reference operator
    val treatFunction = treat
    val sayHelloFunction = sayHello
    sayHelloFunction()
    
    //podemos asignar las funciones desde otra funcion
    val trickFunctionSelected = trickOrTreat(true, null)
    val treatFunctionSelected = trickOrTreat(false, extraCoins)
    //tambien se puede pasar la expresion lambda directamente
    val treatFunctionSimplified = trickOrTreat(false, {"$it cupcakes"})
    //cuando una lambda es el ultimo parametro de una funcion, se puede usar la lambda trailing syntax
    val treatFunctionTrailingSyntax = trickOrTreat(false){
        //la lambda se pasa en el cuelpo en lugar de pasarse con los parametros
        "$it cupckes again"
    }
    //llamada a la expresion lambda
    trickFunctionSelected()
    treatFunctionSelected()
    treatFunctionSimplified()
    //un ejempo de higher order function en la libreria standard de kotlin es repeat
    repeat(3){
        println("repeat")
        treatFunctionTrailingSyntax()
    }
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
//una funcion tambien puede recibir otra funcion como parametro
//y de la misma manera que con el resto de los tipos, las funciones como parametro pueden ser nullable
//esta funcion es una perfecto ejempo de higher order function ya que cumple con lo siguiente:
//es una funcion
//tiene al menos una funcion como parametro
//retorna una funcion
fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if(isTrick){
        return ::trick
    }else{
        if(extraTreat != null){
            println(extraTreat(5))
        }
        return treat
    }
}