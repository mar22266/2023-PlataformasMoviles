package com.example.lab3pm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SixthActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad sexta
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)
        /**
         * Listener para cada boton que nos lleva a cada actividad
         */

        val btnSubMenu51: Button = findViewById(R.id.btnSubMenu51)
        btnSubMenu51.setOnClickListener {
            val intent = Intent(this@SixthActivity, IMG17ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu52: Button = findViewById(R.id.btnSubMenu52)
        btnSubMenu52.setOnClickListener {
            val intent = Intent(this@SixthActivity, IMG18ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu53: Button = findViewById(R.id.btnSubMenu53)
        btnSubMenu53.setOnClickListener {
            val intent = Intent(this@SixthActivity, IMG19ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu54: Button = findViewById(R.id.btnSubMenu54)
        btnSubMenu54.setOnClickListener {
            val intent = Intent(this@SixthActivity, IMG20ACT::class.java)
            startActivity(intent)
        }
    }
}