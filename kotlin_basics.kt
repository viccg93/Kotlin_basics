fun main() {
    //las variables en kotlin se deben de declarar previamente
    
    //val se usa para almacenamiento read-only
    //es preferible usar val siempre que sea posible
    val greeting: String = "Saludos "
    
    //var se usa para valores mutables
    //si se asigna un valor inicial se puede omitir el tipo (type inference)
    var subject = "Victor"
    println(greeting + subject)
    
    //algunos de los tipos comunes son Int, String, Boolean, Float y Double
    
    //String template
    println("Hola $subject desde un string template")
    
    //cuando se actualiza una variable no es necesario usar la palabra reservada var
    
    subject = "Ivan"
    println("$greeting $subject")
    
    //el tipo double tiene 64 bits, mientras el tipo float tiene 32
    
    val roundedPi : Double = 3.1416
    println(roundedPi)
    
    birthdayGreeting("Vic")
    
    println("good to see ya Vic, you've got ${calculateCurrentAge(1993)} years old")
    //named parameter
    println("good to see ya Vic, you've got ${calculateCurrentAge(yearOfBirth = 1993)} years old")
}

//para declarar una funcion se usa la palabra reservada fun
//los parametros deben de inlcuir el tipo de manera explicita
//tambien se debe de especificar el tipo en caso de retornar algun valor
//cuando se omite como en el siguiente metodo, el tipo de retorno es Unit
//el tipo Unit significa que no se devuelve un valor, es similar a void en otros lenguajes
//unit es importante cuando se usan lambdas
fun birthdayGreeting(subject: String) {
    println("Happy Birthday $subject")
}

//cuando una ffuncion devuelve un valor, este se debe de eespecificar explicitamente, excluyendo el caso de Unit
//los parametros son inmutables
fun calculateCurrentAge (yearOfBirth : Int) : Int {
    val currentYear = 2024
    return currentYear - yearOfBirth
}