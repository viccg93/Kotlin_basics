fun main(){
    //estas propiedades son accesibles desde Quiz porque estan marcadas como companion
    //println(Quiz.progressText)
    Quiz.printProgressBar()
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

//para definir extensiones se debe de hacer usando el nombre de la clase
//ademas se debe de asignar a un val o var
//el tip debe de estar definido explicitamente
//las propiedades extension no pueden almacenar valores, por lo que no se pueden usar setters
val Quiz.StudentProgress.progressText: String
//es getter es identico como si la propiedad se hubiera definido de la clase
//answered y total son propiedades del objeto companion StudentProgress
get() = "${answered} of ${total} answered"

//tambien se pueden definir funciones extension, basta solo indicar
//el nombre del tipo debe antes del nombre de la funcion

fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) { print("▓") }
    repeat(Quiz.total - Quiz.answered) { print("▒")}
    println("")
    //se puede acceder a una propiedad extension desde una funcion extension
    println(Quiz.progressText)
}


data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)