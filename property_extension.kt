
fun main(){
    //estas propiedades son accesibles desde Quiz porque estan marcadas como companion
    println("${Quiz.answered} of ${Quiz.total} answered")
}


enum class Difficulty {
    EASY, MEDIUM, HARD
}

class Quiz {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    //se pueden crear objetos singleton usando object en lugar de class
    //este objeto sera la propia instancia ya que no se pueden crear instancias como se hace con las clases
    //un objeto se puede definit como companion para acceder a el desde una instancia
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}


data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)