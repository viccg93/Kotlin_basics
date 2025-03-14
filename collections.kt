fun main() {
    listLogic()
    setsLogic()
    mapLogic()
}

fun arrayLogic() {
    //se puede crear un array usando arrayOf y pasando la lista de elementos
    //un array tiene un tamaño fijo y para aumentar el tamaño se debe de 
    //crear un nuevo array
    val rockPlanets = arrayOf<String>("Earth", "Mars")
    //se puede omitir el tipo debido al type inference
    val gasPlanets = arrayOf("Jupiter", "Saturn")
    
    //se puede hacer una union de arrays usando el operador +
    
    val solarSystem = rockPlanets + gasPlanets
    println(solarSystem[0])
}

fun listLogic() {
    //aunque las interfaces de List y MutableList estan listas para 
    //ser implementadas por cualquier type, generalmente se usan las
    //clases array based, aunque en kotlin no se limita su implementacion
    val solarSystem = listOf("Earth", "Mars","Jupiter", "Saturn")
    //para acceder se puede usar subscription syntax
    println(solarSystem[0])
    //se puede acceder por medio de get()
    println(solarSystem.get(0))
    //se puede obtener el indice por medio del valor, usando indexOf
    println(solarSystem.indexOf("Earth"))
    //loop usando for
    //cuando la variable del for no tiene val ni var, se asume que es get-only
    for(planet in solarSystem){
        println(planet)
    }
    //para agregar elemento se usa el metodo add
    //ese metodo solo esta reservado para los typos que implementan
    //la interface MutableList
    //se puede crear una lista mutable usando el metodo mutableListOf()
    val mutableSolarSystem = mutableListOf("Earth", 
                                           "Mars","Jupiter", "Saturn")
    //el metodo add tiene 2 variantes, la que agrega el elemento al final
    //y la que recibe el indice donde se debe de insertar el elemento
    mutableSolarSystem.add("pluton")
    //cuando se usa el metodo que recibe el indice, este no puede ser mayor
    //al siguiente del ultimo indice actual
    mutableSolarSystem.add(5,"Theia")
    println("mutable solar system")
    for(planet in mutableSolarSystem){
        println(planet)
    }
    //se puede actualizar elementos usando subscription syntax
    mutableSolarSystem[5] = "Future Moon"
    //se pueden eliminar elementos usando remove() y removeAt()
    mutableSolarSystem.removeAt(4)
    mutableSolarSystem.remove("Future Moon")
    //para saber si un elemento existe en una lista se puede usar contains
    //o tambien se puede usar in que es mas consciso
    println(mutableSolarSystem.contains("pluton"))
    println("Future Moon" in mutableSolarSystem)
}

fun setsLogic (){
    //los sets son colecciones que no tienen un orden especifico, y no permitteen valores duplicados
    //estas clases de colecciones tienen un hashcode que se genera usando
    //la funcion hashCode() 
    
    println("kotlin".hashCode())
    println("kotlin!".hashCode())
    println("kotlin".hashCode())
    
    //los sets usan hash codes como indices de array
    //cuando se llema indexOf() revisa la lista de indices desde el inicio hasta la coincidencia
    //generalmente esto resulta en que el tiempo de acceso y de validación sea el mismo
    //los sets usan una mayor cantidad de memoria que una lista
    //el beneficio de los sets es que permiten que los elementos sean unicos
    //siempre que se implemente MutableSet se bede de implementar Set
    //ya que set implementa Set por defecto
    
    val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem.size)
    solarSystem.add("Pluto")
    println(solarSystem.size)
    //verificar si existe un elemento en el set
    println(solarSystem.contains("Pluto"))
    println("Pluto" in solarSystem)
    //si se agrega un elemento existente, no aumenta el tamaño
    solarSystem.add("Pluto")
    println(solarSystem.size)
    //para eliminar se usa el metodo remove
    solarSystem.remove("Pluto")
    println(solarSystem.size) 
}

fun mapLogic() {
    //los mapas se componen de un key que es unico y un value asociado a ese key
    //de la misma manera que con las otras colecciones tenemos mapOf y MutableMapOf
    val solarSystem = mutableMapOf(
        // se usa to para vincular el key y el valor
        "Mercury" to 0,
    	"Venus" to 0,
    	"Earth" to 1,
    	"Mars" to 2,
    	"Jupiter" to 79,
    	"Saturn" to 82,
    	"Uranus" to 27,
    	"Neptune" to 14
    )
    println(solarSystem.size)
    //tambien se puede usar la subscript syntax
    solarSystem["Pluto"] = 5
    println(solarSystem.size)
    
    //de manera alternativa se puede usar el metodo get
    println(solarSystem["Theia"])
    
    //para eliminar una entrada se puede usar remove()
    solarSystem.remove("Pluto")
    println(solarSystem.size)
    
    //para agregar se puede usar put(), si la llave existe, actualiza el valor
    //o se puede usar subscript syntax
    solarSystem["Jupiter"] = 78
	println(solarSystem["Jupiter"])

    
}