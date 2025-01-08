fun main() {
    //a diferencia de Java, en kotlin se debe de indicar explicitamente que un tipo es nullable
    val favoriteActor:String = "Tom Schilling"
    val secondFavoriteActor = null
    //al declarar como string la constante, no se puede asignar null, ya que se asume que es un non-null type
    //val thirdFavoriteActor:String = null
    //para permitir la asignacion de null (nullable type) se debe de incluir ?
    //podria tratarse de un wildcard
    var thirdFavoriteActor: String? = "Zoey Deschanel"
    //se puede asignar ya que el tipo es nullable
    thirdFavoriteActor = null
    
    //asi como el wildcard se indico en String para hacer una variable nullable, se puede usar con otros tipos como Int
    
    var number:Int? = 10
    println(number)
    number = null
    println(number)
    
    //kotlin es null-safety, por lo que una llamada a un componente que puede ser null resulta en un error de compilacion
    var favoriteProgrammer: String? = "Dennis Ritchie"
    var secondFavoriteProgrammer: String = "Bjarne Stroustrup"
    //la siguiente linea falla porque la variable podria contener null, sobre el cual no existe la propiedad length
    //println("la cantidad de caracteres de tu programador favorito son ${favoriteProgrammer.length}")
    //en la siguiente linea no hay problema, ya que el tipo String es considerado non-null type y nunca puede ser null
    println("la cantidad de caracteres de tu segundo programador favorito son ${secondFavoriteProgrammer.length}")
    //kotlin añade el operador safe call ?. el cual permite llamar propiedades de forma segura en nullable types
    //en caso de null, el operador safe call devuelve null
    println("la cantidad de caracteres de tu programador favorito son ${favoriteProgrammer?.length}")
    //alternativamente se puede hacer el null-check al estilo de java usando condicionales (if/else, when)
    //esta alternativa permite realizar alguna accion cuando el componente es null
    if(favoriteProgrammer != null){
        //no se utiliza el operador safe-call ya que el compilador valida el acceso de la propiedad en un contexto donde no es posible null
        println("la cantidad de caracteres de tu programador favorito son ${favoriteProgrammer?.length}")
    }
    //el uso de condicionales permite usar expresiones y no estar restringido a devolver null, contrario al operador safe-call
    val charQuantity = when(favoriteProgrammer){
        null -> 0
        else -> favoriteProgrammer.length
    }
    println("la cantidad de caracteres de tu programador favorito son ${charQuantity}")
    //aunque tal limitacion del operador safe-call, se puede superar si se usa el operador Elvis ?:
    val thirdFavoriteProgrammer: String? = null
    //el operador elvis permite devolver un valor alternativo en caso de null, en este caso 0
    val thirdFavoriteProgrammerLength = thirdFavoriteProgrammer?.length?:0
    println("la cantidad de caracteres de tu tercer programador favorito son ${thirdFavoriteProgrammerLength}")
    //existe la alternativa del operador not-null assertion !!.
    //pero este se debe de usar con cuidado, ya que si bien permite pasar el null-check del proceso de compilacion
    //este falla en runtime si el valor que evalua es null, solo se debe de usar si se esta seguro de que el valor nunca es null
    //si el valor es null, tendremos una NullPointerException
    println("la cantidad de caracteres de tu tercer programador favorito son ${thirdFavoriteProgrammer!!.length}")
    
}