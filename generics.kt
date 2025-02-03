fun main() {
    val question1 = Question(
        questionText = "Quien escribio Mil mesetas",
        answer = "Gilles Deleuze y Felix Guattari",
        difficulty = "medium"
    )
    val question2 = Question(
        questionText = "Cuantos lados tiene un triangulo",
        answer = 3,
        difficulty = "easy"
    )
    val question3 = Question(
        questionText = "Existe DOMMatrix en CSS3",
        answer = true,
        difficulty = "hard"
    )
}

//la estructura para una clase generica es 
//class ClassName <generic data type> (){}

class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: String
)

