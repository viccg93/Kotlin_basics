/*
    Celsius to Fahrenheit: ° F = 9/5 (° C) + 32
    Kelvin to Celsius: ° C = K - 273.15
    Fahrenheit to Kelvin: K = 5/9 (° F - 32) + 273.15
*/
fun main() {
    val initialMeasurement: Double = 10.0 
    val initialUnit: String = "Fahrenheit"
    val finalUnit: String = "Kelvin"
    // se pueden definir cada una de las funciones en forma de variables y posteriormente pasarse como parametro
    val celciusToFahrenheit: (Double) -> Double = {
        (9.0/5.0) * it + 32.0
    }
    
    val kelvinToCelsius: (Double) -> Double = {
        it - 273.15
    }
    
    val fahrenheitToKelvin: (Double) -> Double = {
        (5.0/9.0)*(it - 32.0) + 273.15
    }
    // las funciones se pueden pasar en forma de variable
    if(initialUnit == "Celsius" && finalUnit == "Fahrenheit"){
        printFinalTemperature(initialMeasurement, initialUnit, finalUnit, celciusToFahrenheit)
    }else if(initialUnit == "Kelvin" && finalUnit == "Celsius"){
        // o se pueden pasar directamente como expresion
        printFinalTemperature(initialMeasurement, initialUnit, finalUnit, {it - 273.15})
    }else if(initialUnit == "Fahrenheit" && finalUnit == "Kelvin"){
        // o se pueden pasar como lambda trailing
        printFinalTemperature(initialMeasurement, initialUnit, finalUnit){
        	(5.0/9.0)*(it - 32.0) + 273.15
    	}
    }
}


fun printFinalTemperature(
    initialMeasurement: Double, 
    initialUnit: String, 
    finalUnit: String, 
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
