fun main(){
    //estas propiedades son accesibles desde Quiz porque estan marcadas como companion
    //println(Quiz.progressText)
    //se cambia el siguiente codigo por el de una instancia ya que este metodo ya no esta en el companion object
    Quiz().printProgressBar()
}


enum class Difficulty {
    EASY, MEDIUM, HARD
}

interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}

class Quiz: ProgressPrintable {
    override val progressText: String
    get() = "${answered} of ${total} answered"
    override fun printProgressBar(){
        repeat(Quiz.answered) { print("▓") }
    	repeat(Quiz.total - Quiz.answered) { print("▒")}
    	println("")
    	//ahora se hace referencia a la propiedad implementada de la interface
    	println(progressText)
        
    }
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
//se quita esta extension en favor del uso de una interface
//val Quiz.StudentProgress.progressText: String
//es getter es identico como si la propiedad se hubiera definido de la clase
//answered y total son propiedades del objeto companion StudentProgress
//get() = "${answered} of ${total} answered"

//tambien se pueden definir funciones extension, basta solo indicar
//el nombre del tipo debe antes del nombre de la funcion

//se cambia por la funcion que se implementa de la interface
/*
fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) { print("▓") }
    repeat(Quiz.total - Quiz.answered) { print("▒")}
    println("")
    //se puede acceder a una propiedad extension desde una funcion extension
    println(Quiz.progressText)
}
*/


data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)