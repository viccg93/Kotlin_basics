fun main() {
    //uso de condicionales en kotlin
    val trafficLightColor = "Amber"
    if(trafficLightColor == "red"){
        println("stop")
    }else if(trafficLightColor == "yellow"){
        println("reduce speed")
    }else if (trafficLightColor == "green"){
        println("go on")
    }else{
        println("Stop, something is wrong")
    }
    
    //para evitar el branching del coddigo anterior se puede usar when
    //when evalua en orden, por lo que las opciones posteriores no se ejecutan cuando hay un match en alguna opcion
    
    when(trafficLightColor) {
        "red" -> println("stop")
        "yellow", "Amber" -> println("reduce speed")
        "green" -> println("go on")
        else -> println("Stop, something is wrong")
    }
    
    //encuentra si un valor es un numero primo entre 1 y 10
    //cuando el resultado de evaluar una expresion es repetitiva se puede usar ,
    
    val number = 10
    when (number) {
        2,3,5,7 -> println("el numero es primo entre 1 y 10")
        else -> println("el numero no es primo entre 1 y 10")
    }
    
    
    //tambien se pueden usar rangos en when mediante in
    //los limites de in son inclusivos
    
    //el tipo Any permite indicar que el valor o variable va a ser de cualquier tipo
    val number2: Any = 20.1
    
    when (number2) {
        2,3,5,7 -> println("el numero es primo entre 1 y 10")
        in 1..10 -> println("el numero se encuentra entre 1 y 10, pero no es primo")
        //tambien se puede usar is para averiguar el tipo del parametro
        is Int -> println("el numero es un entero, pero no se encuentra entre 1 y 10")
        else -> println("el valor no es entero")
    }
    
    //tanto if/else como when pueden ser usados como expresiones
    //donde se pueden almacenar distintos valores en funcion de una decision
    
    val message = when(trafficLightColor) {
        "red" -> "stop"
        "yellow", "Amber" -> "reduce speed"
        "green" -> "go on"
        else -> "Stop, something is wrong"
    }
    
    println(message)
    
}