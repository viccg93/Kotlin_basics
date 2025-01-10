//las clases en kotlin se definen de la siguiente manera
//en kotlin existen 2 tipos de constructores, un unico que es el primario y se indica en la definicion de class
//y se pueden definir n o ningun constructor secundario que estan en el cuerpo de la clase
//cuando no se define explicitamente el constructor primario, el compilador lo crea
//para ser consistentes cuando el compilador primario no recibe ningun parametro ni hay anotaciones, se omite la keyword constructor
//y tambien se omiten los parentesis como en la clase SmartDevice
class SmartDevice {
    //cuerpo de la clase
    //las porpiedades pueden ser mutables o inmutables como las "variables"
    val name = "Android TV"
    var deviceStatus = "online"
    
    //los getters y setters se asignan usando un backing field, por lo que todos los getters y setters van a operar con ese backing field
    //cuando no se definen explicitamente, el compilador de kotlin los va a agregar de manera automatica
    //es comun asignar el nombre de field al backing field, pero se debe de terner cuidado de no poner el mismo nombre del campo
    //ya que esa se convertiria en una asignacion que en kotlin llama nuevamente al setter, reniendo como resultado un loop infinito
    var	speakerVolume =  7
    //getter simplificado
    get() = field
    /*getter verbose
     * get(){
        return field
    }*/
    set(value){
        if(value in 1..100){
            field = value
        }else{
            //el valor del volumen debe de estar entre 1 y 100
        }
    }
    
    //las propiedades que son val, por su naturaleza inmutable solo tienen getter
    val productId:String = "DRBSJG1177"
    get() = field
    
    /*error de compilacion
     * set(value){
        field = value
    }*/
    
    //los metodos se definen de la misma manera que las funciones
    fun turnOn() {
        println("el dispositivo esta encendido")
        
    }
    
    fun turnOff() {
        println("el dispositivo esta apagado");
    }
}

//el constructor primario recibe 2 parametros, en primero es la propiedad brandName
//que ya no se encuentra inicializada en el cuerpo, ya que la funcion del constructor primario es inicializar las propiedades
//la propiedad model si se encuentra en el cuerpo y no se inicializa en el constructor primario
    class Laptop constructor ( val brandName: String){
        var model = "00000000"
        get()= field
        set(value){
            //aqui se garantiza que cualquier asignación a model posterior a la construccion cumpla con la longitud adecuada
            if(value.length == 8){
                field = value
            }else{
                field = "00000000"
            }
        }
        //todos los constructores secundarios deben llamar al constructor primario y pasar los parametros necesarios
        //aqui se garantiza la inicialización de model con la longitud adecuada
        constructor(brandName: String, model: String): this(brandName){
            //al tener el mismo nombre la propiedad y el parametro, se usa this
            this.model = when(model.length){
                8->model
                else->"00000000"
            }
        }
    }

fun main(){
    println("clases y objetos en Kotlin")
    //asi se crea una instancia en Kotlin
    val myDevice = SmartDevice()
    var anotherDevice = SmartDevice()
    
   //lamada a una propiedad de una instancia
   println("el nombre del smart device es ${myDevice.name}")
    
    //llamada al metodo de una instancia
    myDevice.turnOn()
    
    //cuando se crea una instancia con val, la "variable" es inmutable pero la instancia es mutable
    //lo que se puede traducir en que no se puede reasignar, pero si se puede modificar el estado
    //de la instancia
    //aunque myDevice es val y no se puede el contenido de la "variable", las propiedades de la instancia que son mutables
    //si se pueden cambiar
    myDevice.deviceStatus = "online"
    //el valor de volumen no se modifica debido a la condicional que se encuentra en el setter
    myDevice.speakerVolume = 200
    println("el volumen de ${myDevice.name} es ${myDevice.speakerVolume}")
    
    //cuando instanciamos Laptop, la propiedad brandName se asigna directamente, mientras que model pasa un procesamiento
    val myLaptop = Laptop(brandName = "Huawei", model = "0123")
    println("laptop ${myLaptop.brandName}, modelo: ${myLaptop.model}")
    myLaptop.model = "12345678"
    println("laptop ${myLaptop.brandName}, modelo: ${myLaptop.model}")
}