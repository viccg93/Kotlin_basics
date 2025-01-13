import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/* Kotlin permite la delegacion de propiedades
 * de la misma manera que los getters y setters utilizan un backing field que referencia a la propiedad
 * uno de los casos donde es util es como cuando se tiene un comportamiento repetitivo y se delega esa tarea
 * esto es aplicable a la validacion de los rangos de las propiedades de las clases de SmartTv y SmartLight
 * aunque es funcional, el codigo es el mismo en todos los casos (la propiedad y la validacion en los setters)
 * esto se puede modificar para delegar tal validación a un tipo delegate
 * la sintaxis de un delegate es var[val] nombre by objeto delegate
 */

//los tipos delegate deben de implementar la interface ReadWriteProperty para el caso de var y ReadOnlyProperty
//para val
class RangeRegulator(
    initValue: Int,
    private val minValue: Int,
    private val maxValue: Int,
) : ReadWriteProperty<Any?, Int> {
    //esta propiedad toma el valor que se pasa al delegate, para ser evaluada en los metodos posteriores
    var fieldData = initValue
    //se implementan los metodos de la interface que son llamados en la asignación y obtencion de la propiedad delegate
    //property contiene los metadatos de una propiedad delegate
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int{
        return fieldData
    }
    
    //evalua el valor que se intenta asignar por medio de value y si cumple con el rango se asigna a fieldData
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int){
        if(value in minValue..maxValue){
            fieldData = value
        }
    }
}

//todas las clases en kotlin son final, por lo que no se pueden usar como parents (extend) por default
//para permitir la herencia se deben marcar como open
//open indica al compilador que la clase se puede extender
//los modificadores de acceso para los constructores se indican despues del nombre de la clase, aun si no hay parametros
open class SmartDevice protected constructor (val name: String, val category: String){
    var deviceStatus = "Off"
    //permite que solo esta clase y sus subclases puedan modificar esta propiedad
    //un set que no realiza ninguna operacion distinta a la de asignar se puede abreviar de la siguiente forma
    protected set
    /*
    protected set(value) {
        field = value
    }*/
    //de la misma manera que los metodos se pueden sobreescribir, las propiedades se pueden sobreescribir con open
    open val deviceType = "Unknown"
    //usando la keyword open en un metodo indica que se le puede hacer override desde las sub-clases
    open fun turnOn() {
        deviceStatus = "On"
    }
    
    open fun turnOff() {
        deviceStatus = "Off"
    }
    
    fun printInfo() {
        println("dispositivo: $name, categoria: $category, tipo: $deviceType")
    }
}

//para usar herencia se debe de usar : y alguno de los constructores de la superclass
//el constructor de SmarTvDevice no tiene indicadores de mutabilidad o inmutabilidad, por lo que no se trata
//de propiedades, solo de parametros que se pasaran al constructor de la clase padre
class SmartTvDevice(deviceName: String, deviceCategory:String):
	SmartDevice(name=deviceName, category=deviceCategory) {
        //se pueden sobreescribir implementaciones de porpiedades de la super class con override
        override val deviceType = "Smart TV"
        //la propiedad no se puede modificar directamente, solo llamando el metodo increaseSpeakerVolume
        //se asigna a la propiedad delegate RangeRegulator
        private var spearkerVolume by RangeRegulator(initValue = 2, minValue = 0, maxValue = 100)
        //lo mismo sirve para channelNumber, solo basta modificar los parametros del constructor de RangeRegulator
        private var channelNumber by RangeRegulator(initValue = 1, minValue = 0, maxValue = 200)
        
        fun increaseSpeakerVolume(){
            spearkerVolume++
            println("el volumen se incremento a $spearkerVolume")
        }
        
        fun decreaseSpeakerVolume(){
            spearkerVolume--
            println("el volumen disminuyo a $spearkerVolume")
        }
        
        //ejemplo de modificador de acceso en metodo
        internal fun nextChannel(){
            channelNumber++
            println("el canal se incremento a $channelNumber")
        }
        
        internal fun previousChannel(){
            channelNumber--
            println("el canal disminuyo a $channelNumber")
        }
        
        override fun turnOn(){
            //con super podemos acceder al metodo de la super class
            super.turnOn()
            println("se encendio $name, el nivel de volumen es $spearkerVolume y el canal es $channelNumber")
        }
        
        override fun turnOff(){
            super.turnOff()
            println("se apago $name")
        }
}
    
class SmartLightDevice(deviceName: String, deviceCategory:String):
	SmartDevice(name=deviceName, category=deviceCategory) {
        override val deviceType = "Smart Light"
        private var brightnessLevel by RangeRegulator(initValue = 0, minValue = 1, maxValue = 100)
        
        fun increaseBrightness() {
            brightnessLevel++
            println("el nivel de brillo se incremento a $brightnessLevel")
        }
        
        fun decreaseBrightness() {
            brightnessLevel--
            println("el nivel de brillo se redujo a $brightnessLevel")
        }
        
        //si el metodo es open, basta indicar el metodo que contiene la implementacion propia con override
        //override le indica al runtime de kotlin que debe ejecutar este codigo en lugar del de la super class
        override fun turnOn() {
            //en este contexto podemos acceder a las propiedades de la super class tambien
            super.turnOn()
            brightnessLevel = 2
            println("se prendio $name, el nivel de brillo es $brightnessLevel")
        }
        
         override fun turnOff() {
             super.turnOff()
             brightnessLevel = 0
             println("se apago $name, el nivel de brillo es $brightnessLevel")
        }
}
    

//existen mutiples 2 tipos de relaciones entre clases
//is-a es una relacion donde una clase es un subtipo de otra, es decir contiene todas las caracteristicas de la superclass
//es unidireccional en el sentido de que la subclase puede hacer todo lo que la superclase, pero la superclase no todo lo que la subclase
//todas las SmartTv son un SmartDevice, pero no todos los SmartDevices son SmartTv
//has-a es cuando un miembro de una clase es de otra clase, es decir la contiene, por ejemplo SmartHome contiene SmartTv
//SmartHome no es un SmartDevice ni visceversa
//modificador de accesibilidad en clases con el constructor default
internal class SmartHome (
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
){
    var deviceTurnOnCount = 0
    //la propiedad solo se puede asignar internamente
    private set
    
    fun printSmartTvInfo(){
        smartTvDevice.printInfo()
    }
    
    fun printSmartLightInfo(){
        smartLightDevice.printInfo()
    }
    
    fun turnOnTv() {
        if(smartTvDevice.deviceStatus == "Off") {
            deviceTurnOnCount++
            smartTvDevice.turnOn()
        }
    }
    fun turnOffTv() {
        if(smartTvDevice.deviceStatus == "On") {
            deviceTurnOnCount--
            smartTvDevice.turnOff()
        }
        
    }
    fun increaseSmartTvDeviceVolume() {
        if(smartTvDevice.deviceStatus == "On"){
            smartTvDevice.increaseSpeakerVolume()
        }
    }
    fun decreaseSmartTvDeviceVolume() {
        if(smartTvDevice.deviceStatus == "On"){
            smartTvDevice.decreaseSpeakerVolume()
        }
    }
    
    fun changeTvChannelToNext() {
        if(smartTvDevice.deviceStatus == "On"){
            smartTvDevice.nextChannel()
        }
    }
    
    fun changeTvChannelToPrevious() {
        if(smartTvDevice.deviceStatus == "On"){
            smartTvDevice.previousChannel()
        }
    }
    
    fun turnOnLight() {
        if(smartLightDevice.deviceStatus == "Off"){
            deviceTurnOnCount++
            smartLightDevice.turnOn()
        }
    }
    fun turnOffLight() {
        if(smartLightDevice.deviceStatus == "On"){
            deviceTurnOnCount--
            smartLightDevice.turnOff()
        }
    }
    fun increaseLightBrightness() {
        if(smartLightDevice.deviceStatus == "On"){
            smartLightDevice.increaseBrightness()
        }
    }
    
    fun decreaseLightBrightness() {
        if(smartLightDevice.deviceStatus == "On"){
            smartLightDevice.decreaseBrightness()
        }
    }
    fun turnOffAllDevices(){
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    //ejemplo de polimorfismo al crear una instancia de la superClass que en algun momento es SmartTv y en otro SnartTv
    var smartDevice: SmartDevice = SmartTvDevice("Android Tv", "Entertainment")
    smartDevice.turnOn()
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
    val smartHome = SmartHome(
        smartTvDevice = SmartTvDevice("Android Tv updated", "Emtertainment"),
        smartLightDevice = SmartLightDevice("Philips Hue", "Utility")
    )
    println("conteo de dispositivos encendidos: ${smartHome.deviceTurnOnCount}")
    smartHome.turnOnTv()
    smartHome.turnOnTv()
    smartHome.turnOnLight()
    smartHome.turnOffTv()
    smartHome.turnOffTv()
    println("conteo de dispositivos encendidos: ${smartHome.deviceTurnOnCount}")
    smartHome.decreaseLightBrightness()
    smartHome.decreaseSmartTvDeviceVolume()
    smartHome.turnOnTv()
    smartHome.changeTvChannelToNext()
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
}