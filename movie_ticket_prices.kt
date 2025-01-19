fun main() {
    val child = 5
    val adult = 28
    val senior = 87
    
    val isMonday = true
    
    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    //cuando se va a evaluar una condicion distinta a la equivalencia, la variable de when va en los casos
    return when {
        age <= 12 -> 15
        age > 12 && age < 61 && isMonday -> 25
        age > 12 && age < 61 -> 30
        age > 60 && age <= 100 -> 20
        else -> -1
    }
}
