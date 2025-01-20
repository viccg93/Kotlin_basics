fun main() {
    val myFoldablePhone = FoldablePhone(isScreenLightOn = false, isFolded = false)
    myFoldablePhone.checkPhoneScreenLight()
    myFoldablePhone.switchOn()
    myFoldablePhone.checkPhoneScreenLight()
    myFoldablePhone.foldPhone()
    myFoldablePhone.checkPhoneScreenLight()
    myFoldablePhone.switchOn()
    myFoldablePhone.checkPhoneScreenLight()
}

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }
    
    fun switchOff() {
        isScreenLightOn = false
    }
    
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone (
    isScreenLightOn:Boolean = false,
	var isFolded:Boolean ): Phone(isScreenLightOn){
    
    override fun switchOn() {
        if(!isFolded){
            isScreenLightOn = true
        }
    }
    
    fun foldPhone() {
        if(!isFolded){
            isFolded = true
            switchOff()
        }
    }
    
    fun unfoldPhone() {
        if(isFolded){
            isFolded = false
        }
    }
}