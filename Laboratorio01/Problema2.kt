/**
 * Problema 2 lab1 ANdre marroquin
 *
 * Clase para guardar los datos con sus 4 atributos para clasificarlos
 */
data class ItemData(
    val originalPos: Int,
    val originalValue: Any?,
    val type: String,
    val info: String?
)

/**
 * Funcion para procesar la lista
 */
fun processList(inputList: List<Any>?): List<ItemData>? {
    val result = mutableListOf<ItemData>()
    if (inputList != null) {
        for ((index, value) in inputList.withIndex()) {
            if (value != null) {
                /**
                 * Se verifica el tipo de dato
                 */
                val type = when (value) {
                    is Int -> "entero"
                    is String -> "cadena"
                    is Boolean -> "booleano"
                    else -> null
                }
                val info = when (value) {
                    is Int -> {
                        when {
                            /**
                             * Saca el modulo de los int para verificar su multiplo
                             */
                            value % 10 == 0 -> "M10"
                            value % 5 == 0 -> "M5"
                            value % 2 == 0 -> "M2"
                            else -> null
                        }
                    }
                    /**
                     * Saca la longitud de la cadena
                     */
                    is String -> "L${value.length}"
                    /**
                     * Saca el valor booleano
                     */
                    is Boolean -> if (value) "Verdadero" else "Falso"
                    else -> null
                }
                /**
                 * Si el tipo no es nulo se agrega a la lista
                 */
                if (type != null) {
                    result.add(ItemData(index, value, type, info))
                }
            }
        }
    }
    /**
     * Si la lista esta vacia retorna nulo
     */
    return if (result.isEmpty()) null else result
}

/**
 * Funcion principal del programa donde se crea y se imprime la lista
 */
fun main() {
    val inputList: Any? = listOf("Hola", true, 30, "PC", null, 3.1416, false, null)
    val result = processList(inputList as List<Any>?)
    result?.forEach { item ->
        println("originalPos: ${item.originalPos}, originalValue: ${item.originalValue}, type: ${item.type}, info: ${item.info}")
    }
}
