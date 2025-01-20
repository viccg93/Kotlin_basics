//import kotlin.properties.ReadWriteProperty
//import kotlin.reflect.KProperty

fun main() {
    val firstSong = Song(title = "Evil", artist = "Interpol", yearPublished = 2004, playCount =  1012900)
    val secondSong = Song(title = "generic indie song", artist = "generic indie band", yearPublished = 2025, playCount =  200)
    
    firstSong.printDescription()
    secondSong.printDescription()
}

class Song constructor (
    val title: String,
    val artist: String,
    val yearPublished: Int) {
    var isPopular: Boolean = false
   	var playCount: Int = 0
    set(value){
        field = value
        if(value >= 1000){
            isPopular = true
        }
    }
    //se descarta esta opcion ya que la actualizacion de playCount no se refleja en el tipo delegado
    //var isPopular: Boolean by PopularMeter(initValue = false, playCounter = this.playCount, popularityLimit = 1000)
    
    
    constructor(title: String, artist: String, yearPublished: Int, playCount: Int): this(title, artist, yearPublished){
        this.playCount = playCount
    }
    
    fun printDescription() {
        println("$title, performed by $artist, was released in $yearPublished")
        if(isPopular){
            println("this song is popular")
        }else{
            println("this song is not popular yet")
        }
    }
    
}

/*
 * se retira el delegado debido a que no se actualiza el valor de playCounter con la instancia de Song

class PopularMeter constructor (
val initValue: Boolean,
val playCounter: Int,
val popularityLimit: Int): ReadWriteProperty<Any?,Boolean> {
    var fieldData = initValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return fieldData
    }
    
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean){
        if(playCounter > popularityLimit){
            fieldData = true
        }
    }
    
}
*/