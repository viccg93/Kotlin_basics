enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

class Event (
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

//extension de Event
    
val Event.durationOfEvent: String
get() = if(this.durationInMinutes < 60){
    "short"
}else {
    "long"
}

fun main() {
    val event = Event(
        title="Study Kotlin",
        description="Commit to studying Kotlin at least 15 minutes per day.",
        daypart=Daypart.EVENING,
        durationInMinutes=15
    )
    
    println(event.title)
    
    val events = mutableListOf(
        Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)
    )
    
     println(events.size)
    
    val shortEvents = events.filter{
        it.durationInMinutes < 60
    }
    
    println("tienes ${shortEvents.size} eventos cortos")
    
    val groupedEvents = events.groupBy{
        it.daypart
    }
    
    groupedEvents.forEach{
        daypart, events ->
        println("${daypart}: ${events.size} events")
    }
    //last() devuelve el ultimo elemento en una coleccion
    println("last event of the day: ${events.last().title}")
    
    //cuando se definen las extensiones se pueden usar como propiedades
    //que estan definidas desde el inicio en la clase
    events.forEach{
        println("${it.durationOfEvent}")
    }
}