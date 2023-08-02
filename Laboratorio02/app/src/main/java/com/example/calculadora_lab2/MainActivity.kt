package com.example.calculadora_lab2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import java.util.regex.Pattern


/**
 * Andre maroquin 22266
 * Laboratorio 2 Plataformas Moviles
 * Calculadora
 * Fuentes utilizadas:
 * https://developer.android.com/topic/libraries/view-binding/migration#groovy -- View Binding migrar error que tenia por versiones
 * https://mathparser.org/ --libreria utilizada para el calculo de la calculadora
 * https://www.google.com/search?q=The+%27kotlin-android-extensions%27+Gradle+plugin+is+no+longer+supported.&rlz=1C1ZKTG_enGT953GT953&oq=The+%27kotlin-android-extensions%27+Gradle+plugin+is+no+longer+supported.&aqs=chrome..69i57j0i512l2.136j0j7&sourceid=chrome&ie=UTF-8#fpstate=ive&vld=cid:a3ab030f,vid:JtnXzdyL64I -- View Binding migrar error que tenia por versiones
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Botones de la calculadora
         */
        button_clear.setOnClickListener {
            input.text = ""
            output.text = ""
        }
        button_left_parentesis.setOnClickListener {
            input.text = AgregarIn("(")
        }
        button_right_parentesis.setOnClickListener {
            input.text = AgregarIn(")")
        }
        button_1.setOnClickListener {
            input.text = AgregarIn("1")
        }
        button_2.setOnClickListener {
            input.text = AgregarIn("2")
        }
        button_3.setOnClickListener {
            input.text = AgregarIn("3")
        }
        button_4.setOnClickListener {
            input.text = AgregarIn("4")
        }
        button_5.setOnClickListener {
            input.text = AgregarIn("5")
        }
        button_6.setOnClickListener {
            input.text = AgregarIn("6")
        }
        button_7.setOnClickListener {
            input.text = AgregarIn("7")
        }
        button_8.setOnClickListener {
            input.text = AgregarIn("8")
        }
        button_9.setOnClickListener {
            input.text = AgregarIn("9")
        }
        button_0.setOnClickListener {
            input.text = AgregarIn("0")
        }
        button_punto.setOnClickListener {
            input.text = AgregarIn(".")
        }
        button_suma.setOnClickListener {
            input.text = AgregarIn("+")
        }
        button_resta.setOnClickListener {
            input.text = AgregarIn("-")
        }
        button_multiplicacion.setOnClickListener {
            input.text = AgregarIn("×")
        }
        button_division.setOnClickListener {
            input.text = AgregarIn("÷")
        }
        button_potencia.setOnClickListener {
            input.text = AgregarIn("^")
        }
        button_modulo.setOnClickListener {
            input.text = AgregarIn("%")

        }
        button_igual.setOnClickListener {
           Resultado()
        }
        button_borrar.setOnClickListener {
            /**
             * Proceso para convertirlo a string y borrar el ultimo caracter del string
             */
            val string = input.text.toString()
            if(string.isNotEmpty()){
                input.text = string.substring(0, string.length - 1)
            }
            output.text = ""
        }

    }


    /**
     * AGREGAR INPUT FUNCION
     */
        private fun AgregarIn(buttonValue: String): String{
            return "${input.text}$buttonValue"
        }

    /**
     * OBTENER EL INPUT FUNCION EN CASO DIVISION Y MULTIPLICACION
     */
        private fun getInput(): String{
            var exp = input.text.replace(Regex("÷"), "/")
            exp = exp.replace(Regex("×"), "*")
            return exp
        }

    /**
     * RESULTADO FUNCION CON LIBRERIA EXTERNA MATH PARSER
     */

        private fun Resultado(){
            try {
                val exp = getInput()
                val result = Expression(exp).calculate()
                if(result.isNaN()){
                    output.text = "Error"
            } else {
                    output.text = DecimalFormat("0.######").format(result).toString()
                    input.text = DecimalFormat("0.######").format(result).toString()
                }
                } catch (e: Exception){
                    output.text = "Error"
            }
        }




}