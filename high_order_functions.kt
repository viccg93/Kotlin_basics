class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
	Cookie(name = "Chocolate chip",
           softBaked = true,
           hasFilling = false,
           price = 1.69
	),
    Cookie(
        name = "Banana Walnut", 
        softBaked = true, 
        hasFilling = false, 
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

fun main() {
    //funciones de primer orden (funciones que reciben y devuelven una funcion)
    //forEach
    //se utiliza lambda trailling
    cookies.forEach{
        cookie -> println("menu item: ${cookie.name}")
    }
    
    //map() permite crear una nueva coleccion a partir de otr
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    println("Full menu:")
    fullMenu.forEach{
        println(it)
    }
    
    //filter() permite crear un sub-comjunto de otra colleccion
    //la coleccion devuelta por filter() es del mismo tipo que la original
    //cuando el resultado de la lambda es true se agrega el elemento a la nueva coleccion
    
    val softBakedMenu = cookies.filter {
        it.softBaked
    }
    
    println("soft-baked menu:")
    softBakedMenu.forEach{
        println("${it.name}")
    }
    
    //groupBy() permite convertir un list a map
    //el objeto resultante sera de tipo Map<Boolean, List<Cookie>>
    val groupedMenu = cookies.groupBy{
        it.softBaked
    }
    
    //como usar subscription syntax en map es nullable, se puede usar
    //elvis operator para devolver una lista vacia
    
    val softBakedCookies = groupedMenu[true] ?: listOf()
    val crunchyCookies = groupedMenu[false] ?: emptyList()
    
    println("soft cookies:")
    softBakedCookies.forEach{
        println("${it.name} - $${it.price}")

    }
    
    println("crunchy cookies:")
    crunchyCookies.forEach{
        println("${it.name} - $${it.price}")

    }
    
    //fold() permitte obtener un valor a partir de una coleccion
    //generalmente esto es util para realizar calculos sobre los elemntos
    //de la collection
    //el primer parametro es el valor inicial y el segundo es una lambda
    //se pued usara lambda trailling
    val totalPrice = cookies.fold(0.0){
        //total es el valor que se acumula y se pasa a totalPrice
        //coockie es el valor de cada elemento de la collection
        total, cookie ->
            //el valor de retorn tambien se pasa a total
            total + cookie.price
    }
    
    println("Total price $${totalPrice}")
    
    //sortedBy() permite obtener una coleccion del mismo tipo pero ordenada
    val alphabeticalMenu = cookies.sortedBy{
        it.name
    }
    
    println("alphabetical menu")
    alphabeticalMenu.forEach{
        println(it.name)
    }
}