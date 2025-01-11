/* Kotlin tiene 4 niveles de visibilidad para las clases, propiedades y metodos, incluidos los getters y setters
 * public permite acceso sin restricciones tanto internas como externas
 * private limita el acceso solo a la clase donde se definen los componentes
 * protected permite el accesso a la clase donde se definen los componentes y a sus subclases
 * internal permite el acceso desde la clase donde se definen los componentes, sus subclases y desde el modulo donde se encuentra
 */

//los getters deben de tener el mismo nivel de accesibilidad que la propiedad a la que pertenecen, en caso contrario resulta en un error de compilacion

fun main() {
    //ejemplo de polimorfismo al crear una instancia de la superClass que en algun momento es SmartTv y en otro SnartTv
    var smartDevice: SmartDevice = SmartTvDevice("Android Tv", "Entertainment")
    smartDevice.turnOn()
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
}

//todas las clases en kotlin son final, por lo que no se pueden usar como parents (extend) por default
//para permitir la herencia se deben marcar como open
//open indica al compilador que la clase se puede extender
//los modificadores de acceso para los constructores se indican despues del nombre de la clase, aun si no hay parametros
open class SmartDevice protected constructor (val name: String, val category: String){
    var deviceStatus = "off"
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
        println("dispositivo $name de la categoria $category")
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
        private var spearkerVolume = 2
        get()=field
        set(value){
            if(value in 0..100){
                field = value
            }
        }
        //esta propiedad tampoco se puede modificar fuera de la clase
        private var channelNumber = 1
        set(value){
            if(value in 0..200){
                field = value
            }
        }
        
        fun increaseSpeakerVolume(){
            spearkerVolume++
            println("el volumen se incremento a $spearkerVolume")
        }
        
        //ejemplo de modificador de acceso en metodo
        internal fun nextChannel(){
            spearkerVolume++
            println("el canal se incremento a $channelNumber")
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
        private var brightnessLevel = 0
        set(value){
            if(value in 1..100){
                field = value
            }
        }
        
        fun increaseBrightness() {
            brightnessLevel++
            println("el nivel de brillo se incremento a $brightnessLevel")
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
    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }
    fun increaseSmartTvDeviceVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }
    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }
    fun turnOffAllDevices(){
        turnOffTv()
        turnOffLight()
    }
}