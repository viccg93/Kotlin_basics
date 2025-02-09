fun main (){
    val question1 = Question(
        questionText = "Kotlin es un lenguaje de Jetbrains, true or false?",
        answer = "true",
        difficulty = Difficulty.EASY
    )
}

//una clase enum se usa para crear tipos con un numero limitado de posibles valores
//los valores posibles del enum se separan con coma
enum class Difficulty {
    EASY, MEDIUM, HARD
}

class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

