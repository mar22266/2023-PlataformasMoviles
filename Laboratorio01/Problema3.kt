/**
 * Problema 3 lab1 andre marroquin
 *
 * Interfaz para las operaciones
 */
interface Operacion {
    fun calcular(): Double
}

/**
 * Clase Padre en este caso con lsa variables que se van a utilizar
 */
open class OperacionBase(val num1: Double, val num2: Double) : Operacion {
    override fun calcular(): Double = 0.0
}

/**
 * Clases hijas se heredan las variables num1 y num2
 */
class Suma(num1: Double, num2: Double) : OperacionBase(num1, num2) {
    override fun calcular(): Double = num1 + num2
}

class Resta(num1: Double, num2: Double) : OperacionBase(num1, num2) {
    override fun calcular(): Double = num1 - num2
}

class Multiplicacion(num1: Double, num2: Double) : OperacionBase(num1, num2) {
    override fun calcular(): Double = num1 * num2
}

class Division(num1: Double, num2: Double) : OperacionBase(num1, num2) {
    override fun calcular(): Double {
        if (num2 != 0.0) {
            return num1 / num2
        } else {
            throw IllegalArgumentException("No se puede dividir entre cero.")
        }
    }
}

/**
 * Funcioin principal del programa
 */
fun main() {
    /**
     * Imprime el menu de operaciones de entrada haciendo el uso de una lista
     */
    val operaciones = listOf("Suma", "Resta", "Multiplicacion", "Division")
    val resultados = mutableListOf<Double>()
    println("Estas son las operaiones:")
    for ((index, operacion) in operaciones.withIndex()) {
        println("${index + 1}. $operacion")
    }

    /**
     * Verifica el input del usuario para ver que operazion desea realizar
     */
    while (true) {
        print("Selecciona lo que desees o 0 para salir: ")
        val opcion = readLine()?.toIntOrNull() ?: continue
        if (opcion == 0) {
            break
        }
        if (opcion in 1..4) {
            print("Ingresa el primer numero: ")
            val num1 = readLine()?.toDoubleOrNull() ?: continue
            print("Ingresa el segundo numero: ")
            val num2 = readLine()?.toDoubleOrNull() ?: continue
            val operacion: Operacion = when (opcion) {
                1 -> Suma(num1, num2)
                2 -> Resta(num1, num2)
                3 -> Multiplicacion(num1, num2)
                4 -> Division(num1, num2)
                else -> continue
            }

            /**
             * Calacula el resultado y lo guarda en la lista
             */
            val resultado = operacion.calcular()
            resultados.add(resultado)
            println("El resultado es: $resultado")
        } else {
            println("ERROR.")
        }
    }
    /**
     * Imprime los resultados anteriores que fueron guardados en una lista
     */
    if (resultados.isNotEmpty()) {
        println("Resultados anteriores:")
        for ((index, resultado) in resultados.withIndex()) {
            println("${index + 1}. $resultado")
        }
    }
}
