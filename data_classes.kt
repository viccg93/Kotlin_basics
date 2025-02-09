fun main (){
    val question1 = Question(
        questionText = "Kotlin es un lenguaje de Jetbrains, true or false?",
        answer = "true",
        difficulty = Difficulty.EASY
    )
    println(question1.toString())
}

//una clase enum se usa para crear tipos con un numero limitado de posibles valores
//los valores posibles del enum se separan con coma
enum class Difficulty {
    EASY, MEDIUM, HARD
}

//una data class no contiene logica, solo sirve para almacenar informacion
//basta con indicar que esta clase es data class para que el compilador de kotlin
//tome ciertas consideraciones como implementar el metodo toString()

//una data class debe tener al menos un parametro en su constructor, este debe ser marcado como val o var
//una data class no debe puede ser abstract, open, sealed o inner
//los metodos que se implementan en una data class son equals(), hashCode(), toString(), componentN(), copy()
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
