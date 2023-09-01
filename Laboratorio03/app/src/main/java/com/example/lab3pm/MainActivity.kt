package com.example.lab3pm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

/**
 * Andre Marroquin
 * Gabriel paz
 * Joaquin Campos
 * Laboratorio 3
 * aplicacion de personalizacion de tarjetas
 */
class MainActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad principal
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Listener para cada boton que nos lleva a cada actividad todos los botones a utilizar definidos en el xml
         */

        val btnOption1: Button = findViewById(R.id.btnOption1)
        btnOption1.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        val btnOption2: Button = findViewById(R.id.btnOption2)
        btnOption2.setOnClickListener {
            val intent = Intent(this@MainActivity, ThirdActivity::class.java)
            startActivity(intent)
        }

        val btnOption3: Button = findViewById(R.id.btnOption3)
        btnOption3.setOnClickListener {
            val intent = Intent(this@MainActivity, FourthActivity::class.java)
            startActivity(intent)
        }

        val btnOption4: Button = findViewById(R.id.btnOption4)
        btnOption4.setOnClickListener {
            val intent = Intent(this@MainActivity, FifthActivity::class.java)
            startActivity(intent)
        }

        val btnOption5: Button = findViewById(R.id.btnOption5)
        btnOption5.setOnClickListener {
            val intent = Intent(this@MainActivity, SixthActivity::class.java)
            startActivity(intent)
        }
    }
}
