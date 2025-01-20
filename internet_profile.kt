fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
       println("Name: $name")
       println("Age: $age")
       var description = ""
       if(hobby != null){
           description = "Likes to $hobby. "
       }
       if(referrer != null){
           description += "has a referer named ${referrer.name}, who likes to ${referrer.hobby}"
       }else{
           description += "Doesn't have a referrer."
       }
       println(description)
       println("")
    }
}
